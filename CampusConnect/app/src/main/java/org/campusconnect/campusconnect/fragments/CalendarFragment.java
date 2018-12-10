package org.campusconnect.campusconnect.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import org.campusconnect.campusconnect.R;
import java.util.Date;

public class CalendarFragment extends Fragment {

    private Toolbar toolbar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    CompactCalendarView compactCalendar;

    public CalendarFragment() {
        // empty public constructor required
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar,container,false);
        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);
        compactCalendar = rootView.findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        Event ev1 = new Event(Color.GREEN, 1544504400000L, "HCI Final Report");
        compactCalendar.addEvent(ev1);
        Event ev2 = new Event(Color.GREEN, 1544763600000L, "Computer Architecture Final Exam");
        compactCalendar.addEvent(ev2);
        Event ev3 = new Event(Color.GREEN, 1545714000000L, "Christmas Day - USA");
        compactCalendar.addEvent(ev3);
        actionBar.setTitle(dateFormatMonth.format(compactCalendar.getFirstDayOfCurrentMonth()));
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getActivity().getApplicationContext();
                if (dateClicked.toString().compareTo("Tue Dec 11 00:00:00 EST 2018") == 0) {
                    Toast.makeText(context, "HCI Final Report Due!", Toast.LENGTH_SHORT).show();
                }else if (dateClicked.toString().compareTo("Fri Dec 14 00:00:00 EST 2018") == 0) {
                    Toast.makeText(context, "Computer Architecture Final Exam", Toast.LENGTH_SHORT).show();
                }else if (dateClicked.toString().compareTo("Tue Dec 25 00:00:00 EST 2018") == 0) {
                        Toast.makeText(context, "Christmas Day - USA", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "No Events Planned for that day", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));

            }
        });
        gotoToday();
        return rootView;
    }
    public void gotoToday() {

        // Set any date to navigate to particular date
        compactCalendar.setCurrentDate(Calendar.getInstance(Locale.getDefault()).getTime());
    }
}
