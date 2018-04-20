package ro.pub.cs.systems.eim.lab07.googlesearcher.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ro.pub.cs.systems.eim.lab07.googlesearcher.R;
import ro.pub.cs.systems.eim.lab07.googlesearcher.network.GoogleSearcherAsyncTask;

public class GoogleSearcherActivity extends Activity {

    private EditText keywordEditText;
    private WebView googleResultsWebView;
    private Button searchGoogleButton;

    private SearchGoogleButtonClickListener searchGoogleButtonClickListener = new SearchGoogleButtonClickListener();
    private class SearchGoogleButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            // TODO exercise 6a)
            // obtain the keyword from keywordEditText
            // signal an empty keyword through an error message displayed in a Toast window
            // split a multiple word (separated by space) keyword and link them through +
            // prepend the keyword with "search?q=" string
            // start the GoogleSearcherAsyncTask passing the keyword

            String keyword = keywordEditText.getText().toString();
            if (keyword.length() == 0) {
                Toast.makeText(GoogleSearcherActivity.this, "ERROR", Toast.LENGTH_LONG).show();
            } else {
                keyword = "search?q=" + keyword.replace(" ", "+");
                new GoogleSearcherAsyncTask(googleResultsWebView).execute(keyword);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_searcher);

        keywordEditText = (EditText)findViewById(R.id.keyword_edit_text);
        googleResultsWebView = (WebView)findViewById(R.id.google_results_web_view);

        searchGoogleButton = (Button)findViewById(R.id.search_google_button);
        searchGoogleButton.setOnClickListener(searchGoogleButtonClickListener);
    }
}
