// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Hex.java

package com.rh.core.util.encoder;

import java.io.*;

// Referenced classes of package com.rh.core.util.encoder:
//			HexEncoder, Encoder

public class Hex
{

	private static final Encoder encoder = new HexEncoder();

	public Hex()
	{
	}

	public static byte[] encode(byte data[])
	{
		return encode(data, 0, data.length);
	}

	public static byte[] encode(byte data[], int off, int length)
	{
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		try
		{
			encoder.encode(data, off, length, bOut);
		}
		catch (IOException e)
		{
			throw new RuntimeException((new StringBuilder()).append("exception encoding Hex string: ").append(e).toString());
		}
		return bOut.toByteArray();
	}

	public static int encode(byte data[], OutputStream out)
		throws IOException
	{
		return encoder.encode(data, 0, data.length, out);
	}

	public static int encode(byte data[], int off, int length, OutputStream out)
		throws IOException
	{
		return encoder.encode(data, off, length, out);
	}

	public static byte[] decode(byte data[])
	{
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		try
		{
			encoder.decode(data, 0, data.length, bOut);
		}
		catch (IOException e)
		{
			throw new RuntimeException((new StringBuilder()).append("exception decoding Hex string: ").append(e).toString());
		}
		return bOut.toByteArray();
	}

	public static byte[] decode(String data)
	{
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		try
		{
			encoder.decode(data, bOut);
		}
		catch (IOException e)
		{
			throw new RuntimeException((new StringBuilder()).append("exception decoding Hex string: ").append(e).toString());
		}
		return bOut.toByteArray();
	}

	public static int decode(String data, OutputStream out)
		throws IOException
	{
		return encoder.decode(data, out);
	}

}
