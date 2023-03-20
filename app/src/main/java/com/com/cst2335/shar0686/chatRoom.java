package com.com.cst2335.shar0686;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.com.cst2335.shar0686.databinding.ActivityChatRoomBinding;
import com.com.cst2335.shar0686.databinding.ReceiveMessageBinding;
import com.com.cst2335.shar0686.databinding.SentMessageBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class chatRoom extends AppCompatActivity {
    ArrayList<ChatMessage> messages;

    ActivityChatRoomBinding binding;
    private RecyclerView.Adapter myAdapter;
    ChatMessageDAO mDAO;
    ChatRoomViewModel chatModel;

    ChatMessage chat = new ChatMessage("", "", false);
    SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
    String currentDateandTime = sdf.format(new Date());


    private void loadMessages() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                messages.addAll(mDAO.getAllMessages());
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);

        MessageDatabase db = MessageDatabase.getInstance(this);
        mDAO = db.chatMessageDAO();

        if (messages == null) {
            chatModel.messages.postValue(messages = new ArrayList<ChatMessage>());
        }

        loadMessages();

        binding.sendButton.setOnClickListener(click -> {
            String message = binding.textInput.getText().toString();
            boolean sentButton = true;
            chat = new ChatMessage(message, currentDateandTime, sentButton);
            messages.add(chat);
            myAdapter.notifyItemInserted(messages.size() - 1);

            binding.textInput.setText("");
        });

        binding.recieve.setOnClickListener(click -> {
            String message = binding.textInput.getText().toString();
            boolean receiveButton = false;
            chat = new ChatMessage(message, currentDateandTime, receiveButton);
            messages.add(chat);
            myAdapter.notifyItemInserted(messages.size() - 1);

            binding.textInput.setText("");
        });


        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if (viewType == 0) {

                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                } else {
//                    ReceiveMessageBindidng binding = ReceiveMessageBinding.inflate(getLayoutInflater());
//                    return new MyRowHolder(binding.getRoot());
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());

                }

            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage obj = messages.get(position);
                holder.message.setText(obj.getMessage());
                holder.time.setText(obj.getTimeSent());
            }

            @Override
            public int getItemCount() {
                return messages.size();
            }

            @Override
            public int getItemViewType(int position) {
                ChatMessage chat = messages.get(position);
                if (chat.isSentButton()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

    }

    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView message;
        TextView time;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(clk -> {

                int position = getAbsoluteAdapterPosition();

                AlertDialog.Builder builder = new AlertDialog.Builder(chatRoom.this);
                builder.setMessage("Do you want to delete the message" + message.getText())
                        .setTitle("Question")
                        .setNegativeButton("No", (dialog, cl) -> {
                        })
                        .setPositiveButton("Yes", (dialog, cl) -> {

                            messages.remove(position);
                            myAdapter.notifyItemRemoved(position);
                        })
                        .create().show();

            });

            message = itemView.findViewById(R.id.messagetext);
            time = itemView.findViewById(R.id.timeText);

        }
    }

}
