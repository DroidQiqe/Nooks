package ata.it.solutions.nooks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author QiqeDev
 *
 */
public class Fragment_Home_Favoritos extends Fragment{

	
	public Fragment_Home_Favoritos(){
		/*
		 * Constructor XD
		 */
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		
		/*
		 * Coding...
		 */
		View vw=inflater.inflate(R.layout.fragment_home_favoritos, container,false);

		
		return vw;
		
	}
}
