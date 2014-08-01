package ata.it.solutions.nooks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LoginFragment extends Fragment implements AnimationListener {
	
	ImageView nookslog;
	Animation AnimBounce;

	 public LoginFragment() {
        /*
         * Se ejecuta antes que el onCreateView
         */

     }
	 

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_login, container, false);
         // Empezar aqui a trabajar con la UI
         
         nookslog=(ImageView)rootView.findViewById(R.id.logologin);
         AnimBounce=AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.bounce);
         AnimBounce.setAnimationListener(this);
         nookslog.startAnimation(AnimBounce);
         return rootView;
     }


	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
