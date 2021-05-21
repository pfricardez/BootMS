package com.springbank.user.query.api.handlers;

import com.springbank.user.query.api.dto.UserLookupResponse;
import com.springbank.user.query.api.queries.*;

public interface UserQueryHandler {
	UserLookupResponse getUserById(FindUserByIdQuery query);
	UserLookupResponse searchUsers(SearchUsersQuery query);
	UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
