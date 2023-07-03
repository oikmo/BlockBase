package org.VoxelTest.toolbox;

import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * This class provides a "nibble" array. A nibble is a number stored on less
 * than 8 bits.
 * 
 * @author Wytrem (4th August 2016)
 */
public class NibbleArray
{
    /**
     * Memory buffer containing all the data. This array indexes don't make
     * sense for nibbles.
     */
    private final byte[] data;

    /**
     * Amount of bits needed to store a nibble.
     */
    private final int depth;

    /**
     * This array size (amount of nibbles in it).s
     */
    private final int size;

    /**
     * Computed from {@code depth}. The bit mask that keeps only the
     * <i>depth</i> lowest bits.
     */
    private final byte mask;

    /**
     * Used for synchronizing common temporary variables.
     */
    private final Object lock = new Object();

    /**
     * Creates a new nibble array of the given size.
     * 
     * @param nibbleDepth A nibble size (the amount of bits needed to stored the
     *        greatest nibble)
     * @param capacity This array size
     */
    public NibbleArray(int nibbleDepth, int capacity)
    {
        if (nibbleDepth > 8 || nibbleDepth < 1)
        {
            throw new IllegalArgumentException();
        }

        int neededBits = nibbleDepth * capacity;

        size = capacity;
        depth = nibbleDepth;
        data = new byte[(neededBits + neededBits % 8) / 8];
        mask = (byte) maskFor(nibbleDepth);
    }

    /**
     * @return This array size
     */
    public int size()
    {
        return size;
    }

    /**
     * Gets the nibble at the given index. This method synchronizes on a lock
     * object, because it uses temporary fields to optimise memory use.
     * 
     * @param index The nibble position in this array
     * @return The nibble at the given position
     */
    public byte get(int index)
    {
        synchronized (lock)
        {
            bitIndex = index * depth;
            byteIndex = bitIndex >> 3;
            bitInByte = bitIndex & 7;
            int value = data[byteIndex] >> bitInByte;

            if (bitInByte + depth > 8)
            {
                value |= data[byteIndex + 1] << bitInByte;
            }

            return (byte) (value & mask);
        }
    }

    /**
     * Gets the nibble at the given index. Async.
     * 
     * @param index The nibble position in this array
     * @return The nibble at the given position
     */
    public byte getAsync(int index)
    {
        int bitIndex = index * depth;
        int byteIndex = bitIndex >> 3;
        int bitInByte = bitIndex & 7;
        int value = data[byteIndex] >> bitInByte;

        if (bitInByte + depth > 8)
        {
            value |= data[byteIndex + 1] << bitInByte;
        }

        return (byte) (value & mask);
    }

    // Temporary fields.
    private transient int bitIndex, byteIndex, bitInByte;

    /**
     * Casts the nibble to byte and puts at the given position.
     * 
     * @param index The position for the new nibble
     * @param nibble The nibble to be put
     */
    public void set(int index, int nibble)
    {
        set(index, (byte) nibble);
    }

    /**
     * Puts the nibble at the given position. This method is Thread safe.
     * 
     * @param index The position for the new nibble
     * @param nybble The nibble to be put
     */
    public void set(int index, byte nybble)
    {
        synchronized (lock)
        {
            bitIndex = index * depth;
            byteIndex = bitIndex >> 3;
            bitInByte = bitIndex & 7;

            // "Clears" the nibble (or nibble piece) at this index, and puts the nibble (or nibble piece)
            data[byteIndex] = (byte) (((~(data[byteIndex] & (mask << bitInByte)) & data[byteIndex]) | ((nybble & mask) << bitInByte)) & 0xff);

            if (bitInByte + depth > 8)
            {
                data[byteIndex + 1] = (byte) (((~(data[byteIndex + 1] & MASKS[bitInByte + depth - 8]) & data[byteIndex + 1]) | ((nybble & mask) >> (8 - bitInByte))) & 0xff);
            }
        }
    }

    /**
     * Computes the binary representation of this array, oredered in
     * {@link ByteOrder#BIG_ENDIAN}.
     * 
     * @return A String of 0 and 1
     */
    public String toBitsString()
    {
        return toBitsString(ByteOrder.BIG_ENDIAN);
    }

    /**
     * Computes the binary representation of this array.
     * 
     * @return A String of 0 and 1
     */
    public String toBitsString(ByteOrder byteOrder)
    {
        StringJoiner joiner = new StringJoiner(" ");

        for (int i = 0; i < data.length; i++)
        {
            joiner.add(binaryString(data[i], byteOrder));
        }

        return joiner.toString();
    }

    /**
     * Clears this array (set every nibble to 0).
     */
    public void clear()
    {
        Arrays.fill(data, (byte) 0);
    }

    /**
     * Sets this nibble at every position.
     * 
     * @param nibble The universal nibble
     */
    public void setAll(byte nibble)
    {
        for (int i = 0; i < size; i++)
        {
            set(i, nibble);
        }
    }

    /**
     * Casts to byte and sets this nibble at every position.
     * 
     * @param nibble The universal nibble
     */
    public void setAll(int nibble)
    {
        for (int i = 0; i < size; i++)
        {
            set(i, (byte) nibble);
        }
    }

    /**
     * Computes the bitmask for the given amount of bits.
     * 
     * @param amountOfBits The amount of bits to be kept by the mask
     * @return The bitmask
     */
    public static int maskFor(int amountOfBits)
    {
        return powerOfTwo(amountOfBits) - 1;
    }

    /**
     * Computes 2<sup>power</sup>.
     * 
     * @param power The power
     * @return 2<sup>power</sup>
     */
    public static int powerOfTwo(int power)
    {
        int result = 1;

        for (int i = 0; i < power; i++)
        {
            result *= 2;
        }

        return result;
    }

    /**
     * Bitmasks for the given amount of bits ({@code MASKS[2] = 0b11 = 3},
     * {@code MASKS[3] = 0b11 = 7}).
     */
    private static final int[] MASKS = new int[8];

    static
    {
        for (int i = 0; i < MASKS.length; i++)
        {
            MASKS[i] = maskFor(i);
        }
    }

    /**
     * Returns the binary String representation from the given byte and the
     * given {@link ByteOrder}.
     * 
     * @param b The byte we want to see as a string
     * @return The String representation of the given byte
     */
    public static String binaryString(byte b, ByteOrder byteOrder)
    {
        String str = String.format("%8s", Integer.toBinaryString(b & 0xff)).replace(' ', '0');

        return byteOrder.equals(ByteOrder.BIG_ENDIAN) ? str : reverse(str);
    }

    /**
     * Reverse the given string (returns it in right to left).
     * 
     * @param str The String to be reversed
     * @return The reversed String
     */
    public static String reverse(String str)
    {
        return new StringBuilder(str).reverse().toString();
    }
}