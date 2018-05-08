package com.example.mypet;

public class Mypet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ... 코드 계속

        // 빈 데이터 리스트 생성.
        final ArrayList<String> items = new ArrayList<String>() ;
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items) ;

        // listview 생성 및 adapter 지정.
        final ListView listview = (ListView) findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;

        // 코드 계속 ...
    }
    Button addButton = (Button)findViewById(R.id.add) ;
        addButton.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
            int count;
            count = adapter.getCount();

            // 아이템 추가.
            items.add("LIST" + Integer.toString(count + 1));

            // listview 갱신
            adapter.notifyDataSetChanged();
        }
    }) ;

}


}
