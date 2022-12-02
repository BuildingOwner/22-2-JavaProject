package main;

public class FrameCount extends Thread {
	int pSync = 1;
	int mSync = 1;
	int frame = 1;
	int pMotion = 1;
	int mMotion = 1;
	static final int MAX_COUNT = 10000;

	public void run() {
		while (true) {
			this.frame++;
			if (this.frame % 10 == 0) {
				this.pSync++;
				this.mSync++;
				if (this.pMotion > 1) {
					this.pMotion++;
				}
				if (this.mMotion > 1) {
					this.mMotion++;
				}
			}
			try {
				this.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (frame > MAX_COUNT) {
				frame = 0;
			}
			if (pSync > MAX_COUNT) {
				pSync = 0;
			}
			if (mSync > MAX_COUNT) {
				mSync = 0;
			}

		}
	}
}
