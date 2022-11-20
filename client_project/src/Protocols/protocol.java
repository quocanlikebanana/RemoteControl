package Protocols;

public enum protocol {
	PROCESS_GET,
	PROCESS_KILL,
	PROCESS_START,
	
	
	
	SCREENSHOT_CLIENT,	// Sent from client
	SCREENSHOT_SERVER,	// Sent from server
	
	
	SUCESS,				// If server performed the request
	FAIL,				// If server can't perform the request
}
