package core.net.event;

import core.ServerConstants;
import core.cache.misc.buffer.ByteBufferUtils;
import core.net.IoSession;
import core.net.IoWriteEvent;

import java.nio.ByteBuffer;

/**
 * Handles the management server handshake write event.
 */
public final class MSHSWriteEvent extends IoWriteEvent {

	/**
	 * Constructs a new {@code MSHSWriteEvent} {@code Object}
	 * @param session The session.
	 * @param context The context.
	 */
	public MSHSWriteEvent(IoSession session, Object context) {
		super(session, context);
	}

	@Override
	public void write(IoSession session, Object context) {
		ByteBuffer	buffer = ByteBuffer.allocate(2 + ServerConstants.MS_SECRET_KEY.length());
		buffer.put((byte) 88);
		ByteBufferUtils.putString(ServerConstants.MS_SECRET_KEY, buffer);
		session.queue((ByteBuffer) buffer.flip());
	}

}