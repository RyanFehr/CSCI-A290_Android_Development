package tech.ryanfehr.androiddevweek4assignmentapplication3;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<FileData> fileList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateFileList();
        populateImageGalleryListView();
    }

    private void populateFileList() {
        String dogContent = "Go look at a dog. Go on, look--maybe at one lying near you right now, curled around his folded legs on a dog bed, or sprawled on his side on the tile floor, paws flitting through the pasture of a dream. Take a good look--and now forget everything you know about this or any dog. This is admittedly a ridiculous exhortation: I don't really expect that you could easily forget even the name or favored food or unique profile of your dog, let alone everything about him. I think of the exercise as analogous to asking a newcomer to meditation to enter into satori, the highest state, on the first go: aim for it, and see how far you get. Science, aiming for objectivity, requires that one becomes aware of prior prejudices and personal perspective. What we'll find, in looking at dogs through a scientific lens, is that some of what we think we know about dogs is entirely borne out; other things which appear patently true are, on closer examination, more doubtful than we thought. And by looking at our dogs from another perspective--from the perspective of the dog--we can see new things that don't naturally occur to those of us encumbered with human brains. So the best way to begin understanding dogs is by forgetting what we think we know.";
        String hobbitContent = "Bilbo was very rich and very peculiar, and had been the wonder of the Shire for sixty years, ever since his remarkable disappearance and unexpected return. The riches he had brought back from his travels had now become a local legend, and it was popularly believed, whatever the old folk might say, that the Hill at Bag End was full of tunnels stuffed with treasure. And if that was not enough for fame, there was also his prolonged vigour to marvel at. Time wore on, but it seemed to have little effect on Mr. Baggins. At ninety he was much the same as at fifty. At ninety-nine they began to call him well-preserved; but unchanged would have been nearer the mark. There were some that shook their heads and thought this was too much of a good thing; it seemed unfair that anyone should possess (apparently) perpetual youth as well as (reputedly) inexhaustible wealth. It will have to be paid for, they said. It isn't natural, and trouble will come of it! But so far trouble had not come; and as Mr. Baggins was generous with his money, most people were willing to forgive him his oddities and his good fortune. He remained on visiting terms with his relatives (except, of course, the Sackville-Bagginses), and he had many devoted admirers among the hobbits of poor and unimportant families. But he had no close friends, until some of his younger cousins began to grow up. ";
        String infernoContent = "He replied: ‘The first, of those you wish to know of, was Empress of many languages, so corrupted by the vice of luxury, that she made licence lawful in her code, to clear away the guilt she had incurred. She is Semiramis, of whom we read, that she succeeded Ninus, and was his wife: she held the countries that the Sultan rules. The next is Dido who killed herself for love, and broke faith with Sichaeus’s ashes: then comes licentious Cleopatra. See Helen, for whom, so long, the mills of war revolved: and see the great Achilles, who fought in the end with love, of Polyxena. See Paris; Tristan; and he pointed out more than a thousand shadows with his finger, naming, for me, those whom love had severed from life.";
        String angelContent = "A knowing groan escaped Langdon's lips. This had happened before. One of the perils of writing books about religious symbology was the calls from religious zealots who wanted him to confirm their latest sign from God. Last month a stripper from Oklahoma had promised Langdon the best sex of his life if he would fly down and verify the authenticity of a cruciform that had magically appeared on her bed sheets. The Shroud of Tulsa, Langdon had called it.";
        String catContent = "i know some good games we could play, said the cat.i know some new tricks, said the cat in hte hat. 'a lot of good tricks. i will show them to you. your mother will not mind at all if i do.";
        String odysseyContent = "But that other cliff, Odysseus, thou shalt note, lying lower, hard by the first: thou couldest send an arrow across. And thereon is a great fig-tree growing, in fullest leaf, and beneath it mighty Charybdis sucks down black water, for thrice a day she spouts it forth, and thrice a day she sucks it down in terrible wise. Never mayest thou be there when she sucks the water, for none might save thee then from thy bane, not even the Earth-Shaker! But take heed and swiftly drawing nigh to Scylla's rock drive the ship past, since of a truth it is far better to mourn six of thy company in the ship, than all in the selfsame hour.";
        String swordContent = "There was a slight chill in the evening air, and Flick clutched the collar of his open wool shirt closer to his neck. His journey ahead lay through forests and rolling flatlands, the latter not yet visible to him as he passed into the forests, and the darkness of the tall oaks and somber hickories reached upward to overlap and blot out the cloudless night sky. The sun had set, leaving only the deep blue of the heavens pinpointed by thousands of friendly stars. The huge trees shut out even these, and Flick was left alone in the silent darkness as he moved slowly along the beaten path. Because he had traveled this same route a hundred times, the young man noticed immediately the unusual stillness that seemed to have captivated the entire valley this evening. The familiar buzzing and chirping of insects normally present in the quiet of the night, the cries of the birds that awoke with the setting of the sun to fly in search of food–all were missing. Flick listened intently for some sound of life, but his keen ears could detect nothing. He shook his head uneasily. The deep silence was unsettling, particularly in view of the rumors of a frightening black-winged creature sighted in the night skies north of the valley only days earlier.";
        fileList.add(new FileData("Dogs", dogContent, new Date(), 75.6));
        fileList.add(new FileData("The Hobbit.txt", hobbitContent, new Date(), 12.2));
        fileList.add(new FileData("Dante's inferno.txt", infernoContent, new Date(), 5));
        fileList.add(new FileData("Angels & Demons.txt", angelContent, new Date(), 23.1));
        fileList.add(new FileData("Cat in the hat.txt", catContent, new Date(), 0.6));
        fileList.add(new FileData("The Odyssey.txt", odysseyContent, new Date(), 83.2));
        fileList.add(new FileData("Sword of Shannarah.txt", swordContent, new Date(), 17.8));
    }

    private void populateImageGalleryListView() {
        ArrayAdapter<FileData> contactArrayAdapter = new GalleryImageAdaptor(fileList);
        ListView fileDataListView = (ListView) findViewById(R.id.fileDataListView);
        fileDataListView.setAdapter(contactArrayAdapter);

        // Setup onClick listener for imageGalleryListView items
        fileDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Build an intent
                Intent intent = new Intent(MainActivity.this, FileDetailActivity.class);
                intent.putExtra("FILE", fileList.get(position));
                // Call the Image Details activity with the newly created intent
                startActivityForResult(intent, 40);
            }
        });
    }

    private class GalleryImageAdaptor extends ArrayAdapter<FileData> {

        private GalleryImageAdaptor(List<FileData> imageList) {
            super(MainActivity.this, R.layout.file_data_layout, imageList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View viewItem = convertView;
            if(viewItem == null) {
                viewItem = getLayoutInflater().inflate(R.layout.file_data_layout, parent, false);
            }
            // get the current view
            FileData currentFileData = fileList.get(position);
            TextView fileNameTextView = (TextView) viewItem.findViewById(R.id.fileNameTextView);
            fileNameTextView.setText(currentFileData.getName());
            TextView dateAndSizeTextView = (TextView) viewItem.findViewById(R.id.dateAndSizeTextView);
            dateAndSizeTextView.setText("Modified: "+currentFileData.getModifiedDate().toString() + " Size: "+currentFileData.getSize()+"mb");

            return viewItem;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_CANCELED) {
            return;
        }

        switch(requestCode) {
            case 40:
                break;
        }
    }
}
