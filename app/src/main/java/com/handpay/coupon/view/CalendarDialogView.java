package com.handpay.coupon.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import com.handpay.coupon.R;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarDialogView extends FrameLayout {

	private CalendarView calendar;
	private ImageButton calendarLeft;
	private TextView calendarCenter;
	private ImageButton calendarRight;
	private SimpleDateFormat format;

	public CalendarDialogView(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.calendar_dialog_view, this);
		init();
	}

	private void init() {
		format = new SimpleDateFormat("yyyy-MM-dd");
		// 获取日历控件对象
		calendar = (CalendarView) findViewById(R.id.calendar);
		calendar.setSelectMore(false); // 单选
		calendarLeft = (ImageButton) findViewById(R.id.calendarLeft);
		calendarCenter = (TextView) findViewById(R.id.calendarCenter);
		calendarRight = (ImageButton) findViewById(R.id.calendarRight);
		// try {
		// // 设置日历日期
		// Date date = format.parse("2015-01-01");
		// calendar.setCalendarData(date);
		//
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		// 获取日历中年月 ya[0]为年，ya[1]为月（格式大家可以自行在日历控件中改）
		String[] ya = calendar.getYearAndmonth().split("-");
		calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
		calendarLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 点击上一月 同样返回年月
				String leftYearAndmonth = calendar.clickLeftMonth();
				String[] ya = leftYearAndmonth.split("-");
				calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
			}
		});

		calendarRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 点击下一月
				String rightYearAndmonth = calendar.clickRightMonth();
				String[] ya = rightYearAndmonth.split("-");
				calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
			}
		});

		// // 设置控件监听，可以监听到点击的每一天（大家也可以在控件中根据需求设
		// OnItemClickListener a = new OnItemClickListener() {
		//
		// @Override
		// public void OnItemClick(Date selectedStartDate, Date selectedEndDate,
		// Date downDate) {
		// if (calendar.isSelectMore()) {
		// Toast.makeText(getContext(), format.format(selectedStartDate) + "到" +
		// format.format(selectedEndDate), Toast.LENGTH_SHORT).show();
		// } else {
		// Toast.makeText(getContext(), format.format(downDate),
		// Toast.LENGTH_SHORT).show();
		//
		// }
		// }
		// };
		// calendar.setOnItemClickListener(a);

	}

	public CalendarView getCalendar() {
		return calendar;
	}

	// 设置日历时间
	public void setCalendarData(Date date) {
		calendar.setCalendarData(date);
		String curYearAndmonth = calendar.getYearAndmonth();
		String[] ya = curYearAndmonth.split("-");
		calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
	}
}
