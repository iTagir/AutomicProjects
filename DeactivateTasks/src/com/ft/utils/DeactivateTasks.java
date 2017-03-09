package com.ft.utils;

import java.io.IOException;
import java.util.Iterator;

import com.uc4.api.Task;
import com.uc4.api.TaskFilter;
import com.uc4.communication.Connection;
import com.uc4.communication.requests.ActivityList;
import com.uc4.communication.requests.CreateSession;
import com.uc4.communication.requests.DeactivateTask;


public class DeactivateTasks {

	public DeactivateTasks() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String hostname = args[0];
		   String username = args[1];
		   String department = args[2];
		   String password = args[3];
		   int client = Integer.parseInt(args[4]);
		   String taskFilterValue = args[5];
		   
		   Connection conn = Connection.open(hostname, 2217);
		   
		   System.out.println("Connecting..." );
		  // CreateSession createSession = new CreateSession();
		   CreateSession createSession = conn.login(client, username, department, password, 'E');
		   System.out.println("Login status " + createSession.isLoginSuccessful());
		   
		   TaskFilter tf = new TaskFilter();
		   
		   if ( taskFilterValue.equals("ANY_OK")) {
		     tf.setStatus(TaskFilter.ANY_OK);
		   } else
		   if ( taskFilterValue.equals("ANY_ABEND")) {
			     tf.setStatus(TaskFilter.ANY_ABEND);
			   } else {
				   System.out.println("Allowed values for task filter are: ANY_OK or ANY_ABEND");
				   System.exit(1);
			   }
		   ActivityList al = new ActivityList(tf);
		   conn.sendRequestAndWait(al);
		   
		   Iterator<Task> iter = al.iterator();
		   while(iter.hasNext()){
			   Task task = iter.next();
			   int runId = task.getRunID();
			   System.out.println("Task:"  + task.getName() + " runID: " + runId);
			   DeactivateTask dt = new DeactivateTask(runId);
			   conn.sendRequestAndWait(dt);
			   //MessageBox mb = dt.getMessageBox();
			   //System.out.println("Message: " + mb.getText());
		   }
		   System.out.println("Done.");
	}

}
