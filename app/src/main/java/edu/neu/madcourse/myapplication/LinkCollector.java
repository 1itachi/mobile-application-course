package edu.neu.madcourse.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LinkCollector extends AppCompatActivity {

    private ArrayList<LinkUnit> linkUnitList;
    private AlertDialog inputAlertDialog;

    private EditText linkNameInput;
    private RecyclerView recyclerView;
    private LinkCollectorViewAdapter linkCollectorViewAdapter;
    private EditText linkUrlInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);
        FloatingActionButton addLinkButton = findViewById(R.id.addLinkButton);

        addLinkButton.setOnClickListener(v -> addLink());

        linkUnitList = new ArrayList<>();
        createInputAlertDialog();
        createRecyclerView();
        linkCollectorViewAdapter.setOnLinkClickListener(position -> linkUnitList.get(position).onLinkUnitClicked(this));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getLayoutPosition();
                linkUnitList.remove(position);
                linkCollectorViewAdapter.notifyDataSetChanged();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    public void createInputAlertDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.activity_link_details_input, null);

        linkNameInput = view.findViewById(R.id.link_name_input);
        linkUrlInput = view.findViewById(R.id.link_url_input);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(view);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(getString(R.string.Add),
                        (dialog, id) -> {
                            LinkUnit linkUnit = new LinkUnit(linkNameInput.getText().toString(), linkUrlInput.getText().toString());
                            if (linkUnit.isValid()) {
                                linkUnitList.add(0, linkUnit);
                                linkCollectorViewAdapter.notifyDataSetChanged();
                                Snackbar.make(recyclerView, getString(R.string.link_add_success), Snackbar.LENGTH_LONG).show();
                            } else {
                                Snackbar.make(recyclerView, getString(R.string.link_invalid), Snackbar.LENGTH_LONG).show();
                            }
                        })
                .setNegativeButton(getString(R.string.Cancel),
                        (dialog, id) -> dialog.cancel());

        inputAlertDialog = alertDialogBuilder.create();
    }

    public void createRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        linkCollectorViewAdapter = new LinkCollectorViewAdapter(this, linkUnitList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(linkCollectorViewAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }


    public void addLink() {
        linkNameInput.getText().clear();
        linkUrlInput.setText(getString(R.string.Http));
        linkNameInput.requestFocus();
        inputAlertDialog.show();
    }

}