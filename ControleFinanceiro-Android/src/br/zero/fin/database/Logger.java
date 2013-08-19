package br.zero.fin.database;

public class Logger {

	private static Logger singletoneLog = new Logger();

	public static Logger global() {
		return singletoneLog;
	}

	public void log(String message) {
		// TODO :)
	}
	
}
