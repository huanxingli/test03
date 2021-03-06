package cn.wischool.ws1app.wischool_androidapp.widge.tasksearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.wischool.ws1app.wischool_androidapp.R;
import cn.wischool.ws1app.wischool_androidapp.ui.BaseFragment;

/**
 * Created by Administrator on 2016/1/6.
 */
public class TaskListFragment extends Fragment {

    private ListView taskList;

    private TaskListViewAdapter adapter = null;

    private List<TaskCellData> list = new ArrayList<TaskCellData>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_task,container,false);
        taskList = (ListView) v.findViewById(R.id.searchListView);
        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList();
    }

    private void initList() {
        for (int i=0;i<DataInput.data.length;i++){
            list.add(DataInput.data[i]);
        }
        adapter = new TaskListViewAdapter(getActivity(),list);

        taskList.setAdapter(adapter);

    }
}
