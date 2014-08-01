package ata.it.solutions.nooks;















import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class Home extends ActionBarActivity implements OnClickListener{
	private DrawerLayout mDrawerLayout;
	private RelativeLayout mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	// SLIDING MENU OPTIONS
		RelativeLayout rlProfile;
		RelativeLayout rlHome;
		RelativeLayout rlSubscriptions;
		RelativeLayout rlHistory;
		RelativeLayout rlSearch;
	    RelativeLayout rlAbout;
	    
	    public static String CUR_PAGE_TITLE = "Title";
		
		private ActionBar actionBar;
		
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
    	initMenu();
    	mTitle = mDrawerTitle = getTitle();
		mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		mDrawerList = (RelativeLayout) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		
		actionBar = getSupportActionBar();
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		  getSupportActionBar().setHomeButtonEnabled(true);
		  getSupportActionBar().setTitle("");
		  
		  mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
					R.drawable.ic_drawer, R.string.drawer_open,
					R.string.drawer_close) 
		  {
			  
			  public void onDrawerClosed(View view) {
				  getSupportActionBar().setTitle(mTitle);
				
				}
				
				

				public void onDrawerOpened(View drawerView) {
					
					getSupportActionBar().setTitle(mDrawerTitle);
					
				}
			  
		  };
		  
		  mDrawerLayout.setDrawerListener(mDrawerToggle);
			if (savedInstanceState == null) {
				cambioFragment(new Fragment_Home_Inicio() );
				setSelected(rlHome);
				//mDrawerLayout.openDrawer(mDrawerList); // Levantamos el proceso para que el menu se carge a la vista del usuario
			}
    	
    	
        
	}
	
	
	
	

	private void cambioFragment(Fragment fragment) {
		// TODO Auto-generated method stub
		mDrawerLayout.closeDrawer(mDrawerList);
		FragmentManager frgManager = getSupportFragmentManager();
		frgManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		
	}





	private void setSelected(RelativeLayout rl) {
		// TODO Auto-generated method stub
		// reset all selections
		rlProfile.setSelected(false);
		rlHome.setSelected(false);
		rlSubscriptions.setSelected(false);
		rlHistory.setSelected(false);
		rlSearch.setSelected(false);
		rlAbout.setSelected(false);
		

		rl.setSelected(true); // set current selection
	}





	/*private void switchFragment() {
		// TODO Auto-generated method stub
		Fragment fragment = null;
		mDrawerLayout.closeDrawer(mDrawerList);

		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
	}*/





	private void initMenu() {
		// TODO Auto-generated method stub
		rlProfile = (RelativeLayout) findViewById(R.id.rlProfile);
		rlHome = (RelativeLayout) findViewById(R.id.rlHome);
		rlSubscriptions = (RelativeLayout) findViewById(R.id.rlSubscriptions);
		rlHistory = (RelativeLayout) findViewById(R.id.rlHistory);
		rlSearch= (RelativeLayout) findViewById(R.id.rlSearch);
		rlAbout= (RelativeLayout) findViewById(R.id.rlAbout);
		
		
		/*
		 * Evento clic en el escuchador de views
		 */
		rlProfile.setOnClickListener(this);
		rlHome.setOnClickListener(this);
		rlSubscriptions.setOnClickListener(this);
		rlHistory.setOnClickListener(this);
		rlSearch.setOnClickListener(this);
		rlAbout.setOnClickListener(this);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	// The action bar home/up action should open or close the drawer.
	// ActionBarDrawerToggle will take care of this.
	if (mDrawerToggle.onOptionsItemSelected(item)) {
	return true;
	}
	return false;
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Fragment fragment = null;
		Bundle args = new Bundle();
		switch (v.getId()) {
		case R.id.rlProfile:
			fragment=new UserProfile();
			setTitle("Profile");
			setSelected(rlProfile);
			break;
			
		case R.id.rlHome:
			fragment=new Fragment_Home_Inicio();
			setTitle("");
			setSelected(rlHome);
			break;
			
		case R.id.rlSubscriptions:
			fragment=new Fragment_Home_Favoritos();
			setTitle("Favoritos");
			setSelected(rlSubscriptions);
			break;
		
		case R.id.rlHistory:
			fragment=new Fragment_Home_Resenias();
			setTitle("Mis reseñas");
			setSelected(rlHistory);
			break;
		case R.id.rlSearch:
			fragment=new Fragment_Home_Search();
			setTitle("Búsquedas");
			setSelected(rlSearch);
			break;
			
		case R.id.rlAbout:
			fragment=new Fragment_Home_About();
			setTitle("Acerca de...");
			setSelected(rlAbout);
			break;

		default:
			break;
		}
		
		
		if (fragment != null) {
			fragment.setArguments(args);
			cambioFragment(fragment);
		} 
		
		
		
		
	}

}
