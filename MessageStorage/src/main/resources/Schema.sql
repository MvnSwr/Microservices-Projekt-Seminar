CREATE TABLE IF NOT EXISTS db__message (
	message_id UUID PRIMARY KEY, 
	send_user_id UUID, 
	recieve_user_id UUID, 
	content VARCHAR(255), 
	date DATE
);