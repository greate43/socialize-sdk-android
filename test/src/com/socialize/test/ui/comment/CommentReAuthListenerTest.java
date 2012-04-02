package com.socialize.test.ui.comment;

import com.google.android.testing.mocking.AndroidMock;
import com.google.android.testing.mocking.UsesMocks;
import com.socialize.error.SocializeException;
import com.socialize.test.ui.SocializeUITestCase;
import com.socialize.ui.comment.CommentButtonCallback;
import com.socialize.ui.comment.CommentReAuthListener;

@UsesMocks ({CommentButtonCallback.class})
public class CommentReAuthListenerTest extends SocializeUITestCase {

	CommentButtonCallback callback;
	CommentReAuthListener listener;
	String comment = "foobar";
	
	final boolean shareLocation = false;
	final boolean subscribe = true;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		callback = AndroidMock.createMock(CommentButtonCallback.class);
		listener = new CommentReAuthListener(getContext(), callback, comment, shareLocation, subscribe);
	}

	@UsesMocks (SocializeException.class)
	public void testOnError() {
		SocializeException error = AndroidMock.createMock(SocializeException.class);
		
		callback.onError(getContext(), error);
		
		AndroidMock.replay(error);
		AndroidMock.replay(callback);
		
		listener.onError(error);
		
		AndroidMock.verify(error);
		AndroidMock.verify(callback);
	}
	
	public void testOnAuthSuccess() {
		
		callback.onComment(comment, shareLocation, subscribe);
		
		AndroidMock.replay(callback);
		
		listener.onAuthSuccess(null);

		AndroidMock.verify(callback);
	}
	
	public void testOnAuthFail() {
		SocializeException error = AndroidMock.createMock(SocializeException.class);
		
		callback.onError(getContext(), error);
		
		AndroidMock.replay(error);
		AndroidMock.replay(callback);
		
		listener.onAuthFail(error);
		
		AndroidMock.verify(error);
		AndroidMock.verify(callback);
	}
}
