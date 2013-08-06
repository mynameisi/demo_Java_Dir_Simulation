package tools;
public final class Msg {
	private Msg() {
	};
	public static void errorMessage(String msg) {
		System.out.println(msg);
		System.exit(1);
	}
}
