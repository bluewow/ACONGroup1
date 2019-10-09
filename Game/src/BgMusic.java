import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BgMusic {
	private static Clip bgClip;
	private static Clip clip;

	public static void Sound(String file, String Options) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(
					new BufferedInputStream(new FileInputStream(file)));
			
			if (Options == "Loop") {
				bgClip = AudioSystem.getClip();
				bgClip.open(ais);
				bgClip.start();
				bgClip.loop(-1);
				System.out.println(bgClip.hashCode());
			} else if (Options == "Stop") {
				bgClip.stop();
				bgClip.close();
				System.out.println(clip.hashCode());
			} else if (Options == "Play") {
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
