package ba.fit.app.e_univerzitet.area_student.primjer6_tickets_post;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Home6Activity extends AppCompatActivity
{

    private LinearLayout rootView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        rootView = new LinearLayout(this);
        setContentView(rootView);

        rootView.setId(View.generateViewId());

        postaviFragment(rootView.getId(), TicketPregledFragment.newInstance());
    }

    private void postaviFragment(int id, Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();
    }

    private final int MENU_NOVI_TICKET = 1;
    private final int MENU_TEST = 2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, MENU_NOVI_TICKET, MENU_NOVI_TICKET, "Novi ticket");
        menu.add(0, MENU_TEST, MENU_TEST, "Test");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case MENU_NOVI_TICKET:
            {
                Toast msg = Toast.makeText(this, "Menu. Novi ticket", Toast.LENGTH_LONG);

                msg.show();

                postaviFragment(rootView.getId(), TicketNewFragment.newInstance());

                return true;
            }
            case MENU_TEST:
            {
                Toast msg = Toast.makeText(this, "Menu. Test", Toast.LENGTH_LONG);
                msg.show();

                return true;
            }
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
