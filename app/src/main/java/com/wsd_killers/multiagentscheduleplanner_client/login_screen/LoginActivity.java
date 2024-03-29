package com.wsd_killers.multiagentscheduleplanner_client.login_screen;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wsd_killers.multiagentscheduleplanner_client.R;
import com.wsd_killers.multiagentscheduleplanner_client.agent.ClientAgent;
import com.wsd_killers.multiagentscheduleplanner_client.todo_tasks_screen.ToDoTasksActivity;

import java.util.logging.Level;

import jade.android.AndroidHelper;
import jade.android.MicroRuntimeService;
import jade.android.MicroRuntimeServiceBinder;
import jade.android.RuntimeCallback;
import jade.core.MicroRuntime;
import jade.core.Profile;
import jade.util.Logger;
import jade.util.leap.Properties;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

import static com.wsd_killers.multiagentscheduleplanner_client.Constans.NetworkUtils.JADE_IP;
import static com.wsd_killers.multiagentscheduleplanner_client.Constans.NetworkUtils.JADE_PORT;

//todo: Wyrzucić metody związane z tworzeniem agenta. Najlepiej do osobnej klasy. Trzymać się MVP.

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private ServiceConnection serviceConnection;
    private MicroRuntimeServiceBinder microRuntimeServiceBinder;
    private Logger logger = Logger.getJADELogger(this.getClass().getName());
    private TextView agentNameTextView;
    private Button registerAgentButton;
    private LoginContract.Presenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginPresenter = new LoginPresenter(this);

        agentNameTextView = findViewById(R.id.agentNamePlainText);
        registerAgentButton = findViewById(R.id.registerAgentButton);
        registerAgentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectToService();
            }
        });
    }

    private void connectToService() {

        serviceConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName className, IBinder service) {
                // Bind successful
                microRuntimeServiceBinder = (MicroRuntimeServiceBinder) service;
                logger.log(Level.INFO, "Gateway successfully bound to MicroRuntimeService");
                startContainer();

            }

            @Override
            public void onServiceDisconnected(ComponentName className) { // Bind unsuccessful
                microRuntimeServiceBinder = null;
                logger.log(Level.INFO, "Gateway unbound from MicroRuntimeService");

            }
        };

        logger.log(Level.INFO, "Binding Gateway to MicroRuntimeService...");

        bindService(new Intent(getApplicationContext(), MicroRuntimeService.class), serviceConnection,
                Context.BIND_AUTO_CREATE);
    }

    private void startContainer() {

        Properties pp = new Properties();
//        pp.setProperty(Profile.MAIN_HOST, "192.168.0.10"); // zmiencie ip na wasze
        pp.setProperty(Profile.MAIN_HOST, JADE_IP); // zmiencie ip na wasze
        pp.setProperty(Profile.MAIN_PORT, JADE_PORT);
        pp.setProperty(Profile.JVM, Profile.ANDROID);

        if (AndroidHelper.isEmulator()) {
            // Emulator: this is needed to work with emulated devices
            pp.setProperty(Profile.LOCAL_HOST, AndroidHelper.LOOPBACK);
        } else {
            pp.setProperty(Profile.LOCAL_HOST,
                    AndroidHelper.getLocalIPAddress());
        }
//         Emulator: this is not really needed on a real device
        pp.setProperty(Profile.LOCAL_PORT, "2000");

        microRuntimeServiceBinder.startAgentContainer(pp, new RuntimeCallback<Void>() {

            @Override
            public void onSuccess(Void thisIsNull) {
                // Split container startup successful
                logger.log(Level.INFO, "Successfully start of the container...");
                startAgent(agentNameTextView.getText().toString(), agentStartupCallback);
            }

            @Override
            public void onFailure(Throwable throwable) {
                // Split container startup error
            }
        });
    }

    private void startAgent(
            final String nickname,
            final RuntimeCallback<AgentController> agentStartupCallback) {

        microRuntimeServiceBinder.startAgent(
                nickname,
                ClientAgent.class.getName(),
                new Object[]{getApplicationContext()},
                new RuntimeCallback<Void>() {
                    @Override
                    public void onSuccess(Void thisIsNull) {
                        logger.log(Level.INFO, "Successfully start of the "
                                + ClientAgent.class.getName() + "...");
                        try {
                            agentStartupCallback.onSuccess(MicroRuntime
                                    .getAgent(nickname));
                            openClientActivity();
                        } catch (ControllerException e) {
                            // Should never happen
                            agentStartupCallback.onFailure(e);
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        logger.log(Level.SEVERE, "Failed to start the "
                                + ClientAgent.class.getName() + "...");
                        agentStartupCallback.onFailure(throwable);
                    }
                });
    }

    private void openClientActivity() {
        Intent intent = new Intent(this, ToDoTasksActivity.class);
        intent.putExtra("agentName", agentNameTextView.getText().toString());
        startActivity(intent);
    }

    private RuntimeCallback<AgentController> agentStartupCallback = new RuntimeCallback<AgentController>() {

        @Override
        public void onSuccess(AgentController agent) {
        }

        @Override
        public void onFailure(Throwable throwable) {
            logger.log(Level.INFO, "Nickname already in use!");
        }
    };
}
