package sql.schema;

/**
 * Ascii progress meter. On completion this will reset itself,
 * so it can be reused
 * <br /><br />
 * 100% ################################################## |
 */
public class ProgressBar {
	private StringBuilder progress;
	private char[] workchars = {'|', '/', '-', '\\'};

	/**
	 * initialize progress bar properties.
	 */
	public ProgressBar() {
		init();
	}

	/**
	 * called whenever the progress bar needs to be updated.
	 * that is whenever progress was made.
	 *
	 * @param done an int representing the work done so far
	 * @param total an int representing the total work
	 * @param the absolute amount of work done on every iteration
	 */
	public void update(int done, int total, int actualTotal) {
		String format = "\r%3d%% %s %c %s";

		int percent = (++done * 100) / total;
		int extrachars = (percent / 2) - this.progress.length();

		while (extrachars-- > 0) {
			progress.append('#');
		}
		
		System.out.printf(format, percent, progress,
				workchars[done % workchars.length], done != total && actualTotal != Integer.MIN_VALUE ? actualTotal : "");

		if (done == total) {
			System.out.print(actualTotal);
			System.out.flush();
			System.out.println();
			init();
		}
	}

	private void init() {
		this.progress = new StringBuilder(60);
	}

}
