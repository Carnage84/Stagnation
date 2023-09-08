package core.game.system;

import core.tools.Log;

import static core.api.ContentAPIKt.log;

/**
 * Handles the shutdown hook.
 */
public final class SystemShutdownHook implements Runnable {

	@Override
	public void run() {
		if (SystemManager.isTerminated()) {
			return;
		}
		log(this.getClass(), Log.INFO, "Terminating...");
		SystemManager.flag(SystemState.TERMINATED);
	}
}