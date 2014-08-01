package ata.it.solutions.nooks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 
 * @author QiqeDev
 *
 */
public class Fragment_Home_About extends Fragment{
	
public Fragment_Home_About(){
	/*
	 * Constructor
	 */
}

@Override
public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
	View vw=inflater.inflate(R.layout.fragment_home_about, container,false);
	return vw;
}
}
