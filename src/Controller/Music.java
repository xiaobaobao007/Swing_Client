package Controller;

import java.io.FileInputStream;
import java.util.Random;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Music {

    static int size = 3;
    static int start = new Random().nextInt(size);
    String[] files = {"boom", "shoot"};

    public Music() {
        try {
            if (++start > size) {
                start = 1;
            }
            FileInputStream fileau = new FileInputStream("resouce/music/bgm" + start + ".wav");
            AudioStream as = new AudioStream(fileau);
            AudioPlayer.player.start(as);
            while (true) {
                if (as.available() == 0) {
                    new Music();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Music(int type, int id) {
        String file = "resouce/music/" + files[type] + "" + id + ".wav";
        try {
            FileInputStream filea = new FileInputStream(file);
            AudioStream as = new AudioStream(filea);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            System.out.print(file);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Music(0, 1);
//		new Music(1,1);
//		new Music(1,2);
//		new Music(1,3);
    }

}
