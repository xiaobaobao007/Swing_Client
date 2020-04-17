package Controller;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Random;

public class Music {

	static int size = 3;
	static int start = new Random().nextInt(size);
	static String[] files = {"boom", "shoot"};

	public Music() {
		try {
			FileInputStream fileInputStream;
			BufferedInputStream buffer;
			Player player;
			while (true) {
				if (++start > size) {
					start = 1;
				}
				fileInputStream = new FileInputStream("resouce/music/bgm" + start + ".mp3");
				buffer = new BufferedInputStream(fileInputStream);
				player = new Player(buffer);
				player.play();
				while (true) {
					Thread.sleep(5000);
					if (player.isComplete()) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void play(int type, int id) {
		new Thread(() -> {
			try {
				String file = "resouce/music/" + files[type] + "" + id + ".mp3";
				FileInputStream fileInputStream = new FileInputStream(file);
				BufferedInputStream buffer = new BufferedInputStream(fileInputStream);
				Player player = new Player(buffer);
				player.play();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	public static void main(String[] args) throws Exception {
		String file = "resouce/music/bgm1.mp3";
		BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(file));
		Player player = new Player(buffer);
		player.play();
//		buffer.close();
//		player.close();
	}

}
