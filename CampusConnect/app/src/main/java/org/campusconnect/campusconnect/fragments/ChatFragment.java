package org.campusconnect.campusconnect.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.campusconnect.campusconnect.R;
import org.campusconnect.campusconnect.model.ChatMessage;

import static android.app.Activity.RESULT_OK;

public class ChatFragment extends Fragment implements View.OnClickListener {
    private FloatingActionButton fab;
    private FirebaseListAdapter<ChatMessage> adapter;
    private TextInputEditText input;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat,container,false);
        input = rootView.findViewById(R.id.input);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        ListView listOfMessages = rootView.findViewById(R.id.list_of_messages);
        Query query = FirebaseDatabase.getInstance().getReference().child("Chats");
        FirebaseListOptions<ChatMessage> options =
                new FirebaseListOptions.Builder<ChatMessage>()
                        .setQuery(query, ChatMessage.class)
                        .setLayout(R.layout.message)
                        .build();
        adapter = new FirebaseListAdapter<ChatMessage>(options) {
            @Override
            protected void populateView(View rootView, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = rootView.findViewById(R.id.message_text);
                TextView messageUser = rootView.findViewById(R.id.message_user);
                TextView messageTime = rootView.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };
        listOfMessages.setAdapter(adapter);
        fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        return rootView;
    }

    /*
    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
    */

    @Override
    public void onClick(View v) {
        // Read the input field and push a new instance
        // of ChatMessage to the Firebase database
        FirebaseDatabase.getInstance()
                .getReference()
                .child("Chats")
                .push()
                .setValue(new ChatMessage(input.getText().toString(),
                        mCurrentUser.getDisplayName())
                );
        // Clear the input
        input.setText("");
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
