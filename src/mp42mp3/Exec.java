package mp42mp3;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Exec {
	public static void main(String[] args) throws Exception {
		//ffmpeg -i input.mp4 -vn -ab 192k -ar 44100 output.mp3
		String ffmpegSrc = "D:/mp42mp3/ffmpeg-2024-03-25-git-ecdc94b97f-full_build/ffmpeg-2024-03-25-git-ecdc94b97f-full_build/bin/ffmpeg.exe";
		String inputSrc = "D:/mp42mp3/in";
		String outputSrc = "D:/mp42mp3/out";
		
		for (String string1 : new File(inputSrc).list()) {
			String string = string1;
			string = string1.replaceAll(" ", "");
			new File(inputSrc+"/"+string1).renameTo(new File(inputSrc+"/"+string));
			
			
			if (string.toUpperCase().endsWith("MP4")) {
				System.out.println(string);
				String input = inputSrc+"/"+string;
				String output = outputSrc+"/"+string.substring(0,string.lastIndexOf('.'))+".mp3";
				String cmd = ffmpegSrc+" -i "+input+" -vn -ab 192k -ar 44100 "+output;
				System.out.println(cmd);
				Process process = Runtime.getRuntime().exec(cmd);
				
	            InputStream inputStream = process.getErrorStream();
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	            String line;
	            while ((line = bufferedReader.readLine()) != null) {
	                System.out.println(line);
	            }

	            bufferedReader.close();
	            inputStreamReader.close();
	            inputStream.close();

	            int exitCode = process.waitFor();
	            System.out.println("Exit code: " + exitCode);
			}
			
			
		}
			
		
		
		
	}
}
