package com.example.shivam.adminapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class CreateBatchActivity extends AppCompatActivity implements View.OnClickListener {
    //A batch would be created here
    EditText batch_major,stream,start,end;
    Button submit;
    EditText filename;
    Button  uploadbutton;
    ArrayList<Student> arrayList;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_batch);
        //Everything would be done here, from adding students to assigning starting and ending dates to the course
        //Name of course
        //Duration
        //Semester Calendar would be uploaded in the form of excel sheet --> first semester, second semester, third semester, etc
        // Semester - > Duration, start date, end date, subjects(where there is no data filled, default has to be written in the excel sheet)

        //TODO : Among all the steps to be done, this is the first step.
        //Input name of Batch
        //Starting year - Ending Year
        //Stream
        //Jump to next screen on submit and provide the option of adding the students by uploading their data

        // For now, only simple prototype to add students and assign faculty to batch
        filename=findViewById(R.id.filename);
        uploadbutton=findViewById(R.id.uploadfile);
        uploadbutton.setOnClickListener(this);
        arrayList=new ArrayList<>();
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.uploadfile:
                String sfilename=filename.getText().toString();
                readExcelFile(CreateBatchActivity.this,sfilename);

                break;
        }
    }
    private void readExcelFile(Context context, String filename) {

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly())
        {
            Log.w("FileUtils", "Storage not available or read only");
            return;
        }

        try{
            // Creating Input Stream
            File file = new File(context.getExternalFilesDir(null), filename);
            FileInputStream myInput = new FileInputStream(file);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<Row> rowIter = mySheet.rowIterator();
            rowIter.next();
            while(rowIter.hasNext()){
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator<Cell> cellIter = myRow.cellIterator();
                String values[]=new String[7];
                int i=0;
                while(cellIter.hasNext()){
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    values[i]=myCell.toString();
                    i++;
                    Log.w("FileUtils", "Cell Value: " +  myCell.toString());
                    Toast.makeText(context, "cell Value: " + myCell.toString(), Toast.LENGTH_SHORT).show();
                }
                String id  = values[0];
                id =  id.replace(".","");
                id  = id.replace("E10","");


                Student student=new Student(id,values[1],values[2],values[3],values[4],values[5],values[6]);
                arrayList.add(student);


            }
            MyTask myTask=new MyTask();
            myTask.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

        return;
    }
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
    class MyTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("ddMMyyyy");
            String sdate=simpleDateFormat.format(date).toString().trim();
            String batch_id =  arrayList.get(0).getCourse().toString();
            String stream =  arrayList.get(0).getBranch().toString();
            databaseReference= FirebaseDatabase.getInstance().getReference().child("GGSIPU").child(batch_id)
                    .child(stream);


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            int size=arrayList.size();
            for(int i=0;i<size;i++){
                arrayList.remove(arrayList.size()-1);
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for( int i=0;i<arrayList.size();i++){
                inputdata(arrayList.get(i));

            }

            return null;


        }
        private void inputdata(final Student student){


            databaseReference.child(student.getEnrollNo().toString()).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.e("DATAENTRY","SUCCESS");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("DATAENTRY","FAILED");
                }
            });

        }
    }

}
