package com.springbank.user.query.api.handlers;

import com.springbank.user.core.events.*;

public interface UserEventHandler {
	void on(UserRegisteredEvent event);
	void on(UserUpdatedEvent event);
	void on(UserRemovedEvent event);
}
