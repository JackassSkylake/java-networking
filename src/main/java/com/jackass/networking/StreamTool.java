/**
 * 
 */
package com.jackass.networking;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jackass
 *
 */
public class StreamTool {
	private StreamTool() {
	}

	public static String inputStreamToStr(InputStream in, String encoding) throws Exception {
		String ret = null;
		if (in != null) {
			ret = new String(inputStreamToByteArray(in), encoding);
		}
		return ret;
	}

	public static byte[] inputStreamToByteArray(InputStream in) throws IOException {
		byte[] ret = null;
		if (in != null) {
			int len;
			byte[] data = new byte[1024];
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			while ((len = in.read(data)) != -1) {
				out.write(data, 0, len);
			}
			ret = out.toByteArray();
		}
		return ret;
	}
}
