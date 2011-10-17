package com.socialize.ui.test.comment;

import com.google.android.testing.mocking.AndroidMock;
import com.google.android.testing.mocking.UsesMocks;
import com.socialize.error.SocializeException;
import com.socialize.ui.comment.CommentButtonCallback;
import com.socialize.ui.comment.CommentReAuthListener;
import com.socialize.ui.test.SocializeUITestCase;

@UsesMocks ({CommentButtonCallback.class})
public class CommentReAuthListenerTest extends SocializeUITestCase {

	CommentButtonCallback callback;
	CommentReAuthListener listener;
	String comment = "foobar";
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		callback = AndroidMock.createMock(CommentButtonCallback.class);
		listener = new CommentReAuthListener(getContext(), callback, comment);
	}

	@UsesMocks (SocializeException.class)
	public void testOnError() {
//		String errorMessage = "foobar_message";
		SocializeException error = AndroidMock.createMock(SocializeException.class);
		
		callback.onError(getContext(), error);
		
		AndroidMock.replay(error);
		AndroidMock.replay(callback);
		
		listener.onError(error);
		
		AndroidMock.verify(error);
		AndroidMock.verify(callback);
	}
	
	public void testOnAuthSuccess() {
		
		callback.onComment(comment);
		
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
