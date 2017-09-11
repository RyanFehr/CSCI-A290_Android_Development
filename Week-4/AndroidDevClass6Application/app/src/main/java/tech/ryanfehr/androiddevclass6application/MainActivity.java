package tech.ryanfehr.androiddevclass6application;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateContacts();
        populateUserContactListView();
    }

    private void populateContacts() {
        contacts.add(new Contact("Ryan", "Fehr", "812-946-4807", "rfehr@iu.edu", R.drawable.identicon_light_green));
        contacts.add(new Contact("Jeff", "Bezos", "812-867-5309", "jbezz@amazon.com", R.drawable.identicon_light_blue));
        contacts.add(new Contact("Steve", "Jobs", "812-855-3147", "sjobs@apple.com", R.drawable.identicon_purple));
        contacts.add(new Contact("Sergey", "Brin", "812-843-6705", "sgbrin@google.com", R.drawable.identicon_green));
        contacts.add(new Contact("Elon", "Musk", "812-123-3456", "emusk@tesla.com", R.drawable.identicon_orange));
        contacts.add(new Contact("Bill", "Gates", "940-654-3210", "bgates@microsoft.com", R.drawable.identicon_lime));
    }

    private void populateUserContactListView() {
        ArrayAdapter<Contact> contactArrayAdapter = new ContactAdaptor(contacts);
        ListView userContactListView = (ListView) findViewById(R.id.userContactListView);
        userContactListView.setAdapter(contactArrayAdapter);
    }

    private class ContactAdaptor extends ArrayAdapter<Contact> {

        public ContactAdaptor(List<Contact> contactList) {
            super(MainActivity.this, R.layout.item_layout, contactList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View viewItem = convertView;
            if(viewItem == null) {
                viewItem = getLayoutInflater().inflate(R.layout.item_layout, parent, false);
            }
            // get the current view
            Contact currentContact = contacts.get(position);

            ImageView viewIcon = (ImageView) viewItem.findViewById(R.id.item_profileIcon);
            viewIcon.setImageResource(currentContact.getIconID());
            TextView viewFullName = (TextView) viewItem.findViewById(R.id.item_fullName);
            viewFullName.setText(currentContact.getFirstName() + " " + currentContact.getLastName());
            TextView viewPhoneNumber = (TextView) viewItem.findViewById(R.id.item_phoneNumber);
            viewPhoneNumber.setText(currentContact.getPhoneNumber());
            TextView viewEmailAddress = (TextView) viewItem.findViewById(R.id.item_emailAddress);
            viewEmailAddress.setText(currentContact.getEmailAddress());
//            return super.getView(position, convertView, parent);
            return viewItem;
        }
    }
}
