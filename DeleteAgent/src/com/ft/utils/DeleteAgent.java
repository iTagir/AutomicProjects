package com.ft.utils;


import java.io.IOException;

import com.uc4.api.systemoverview.AgentListItem;
import com.ftapi.Config;
import com.ftapi.ConfigImpl;
import com.ftapi.FailedToStopAgent;
import com.ftapi.Login;
import com.ftapi.LoginFailedException;
import com.ftapi.LoginImpl;
import com.ftapi.NotFoundException;
import com.ftapi.AgentOperations;
import com.uc4.communication.Connection;

public class DeleteAgent {
	public static void main(String[] args) throws IOException, LoginFailedException, NotFoundException, InterruptedException, FailedToStopAgent {
	String server = args[0];
	String port = args[1];
	String client = "0";
	String user = "UC";
	String department = "UC";
	String password = args[2];
	String agentName = args[3];
	
	Config config = new ConfigImpl(server,port,client,user,department,password);
	Login login = new LoginImpl(config);
	Connection conn = login.login();
	AgentOperations agentOps = new AgentOperations(conn);
	AgentListItem agentObject = agentOps.getAgentByName(agentName);
	agentOps.deleteAgent(agentObject.getName().getName());
	System.out.println("info: deleted: " + agentObject.getName());
	login.logout();
	}
}
