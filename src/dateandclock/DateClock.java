package dateandclock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
class Cpanel extends Panel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Label l1=new Label("");
	Label l2=new Label("");
	Label l3=new Label("");

	Thread timer = null;
	int lastxs=143, lastys=60, lastxm=160, lastym=76, lastxh=170, lastyh=90;

	Cpanel(){
		start();

	}
	public void paint(Graphics g)  //��ʾ���ֺ�ͼ��ʱ�ӵķ���
	{	
		int xh, yh, xm, ym, xs, ys, s, m, h, xcenter, ycenter;
		LocalTime time=LocalTime.now();
		LocalDate date=LocalDate.now();
		s = time.getSecond();
		m = time.getMinute();
		h = time.getHour();
		l1.setText(h+":"+m+":"+s);
		l2.setText(date.toString());
		l3.setText((date.getDayOfWeek()).toString());
		xcenter=75+75; ycenter=50+75; //ͼ���ӵ�ԭ��
		//���¼������롢���롢ʱ��λ��
		xs = (int)(Math.cos(s * 3.14f/30 - 3.14f/2) * 65 + xcenter);
		ys = (int)(Math.sin(s * 3.14f/30 - 3.14f/2) * 65 + ycenter);
		xm = (int)(Math.cos(m * 3.14f/30 - 3.14f/2) * 50 + xcenter);
		ym = (int)(Math.sin(m * 3.14f/30 - 3.14f/2) * 50 + ycenter);
		xh = (int)(Math.cos((h*30+m/2)*3.14f/180-3.14f/2)*40+xcenter);
		yh = (int)(Math.sin((h*30+m/2)*3.14f/180-3.14f/2)*40+ycenter);
		g.setFont(new Font("TimesRoman", Font.BOLD, 14));
		g.setColor(Color.WHITE); //���ñ�����ɫ
		g.fillOval(xcenter-78,ycenter-78,156,156); //������
		g.setColor(Color.GRAY); //���ñ�����ɫ
		g.fillOval(xcenter-75,ycenter-75,150,150); //������

		g.setColor(Color.black); //���ñ���������ɫ
		g.drawString("9",xcenter-70,ycenter+3); //�������ϵ�����
		g.drawString("3",xcenter+64,ycenter+3);
		g.drawString("12",xcenter-6,ycenter-60);
		g.drawString("6",xcenter-3,ycenter+70);
		//ʱ��仯ʱ����Ҫ���»�����ָ�룬��������ԭ��ָ�룬Ȼ����ָ��
		g.setColor(Color.GRAY); //�ñ�����ɫ���ߣ���������ԭ��������
		if (xs != lastxs || ys != lastys){ //����仯
			g.drawLine(xcenter, ycenter, lastxs, lastys); }
		if (xm != lastxm || ym != lastym) { //����仯
			g.drawLine(xcenter, ycenter-1, lastxm, lastym);
			g.drawLine(xcenter-1, ycenter, lastxm, lastym); }
		if (xh != lastxh || yh != lastyh) { //ʱ��仯
			g.drawLine(xcenter, ycenter-1, lastxh, lastyh);
			g.drawLine(xcenter-1, ycenter, lastxh, lastyh); }
		g.setColor(Color.black); //����ָ��
		g.drawLine(xcenter, ycenter, xs, ys);
		g.drawLine(xcenter, ycenter-1, xm, ym);
		g.drawLine(xcenter-1, ycenter, xm, ym);
		g.drawLine(xcenter, ycenter-1, xh, yh);
		g.drawLine(xcenter-1, ycenter, xh, yh);
		lastxs=xs; lastys=ys; //����ָ��λ��
		lastxm=xm; lastym=ym;
		lastxh=xh; lastyh=yh; 
	}
	public void start() { //�����̵߳ķ���
		if(timer == null) {
			timer = new Thread(this);
			timer.start(); 
		}
	}
	public void stop() //ֹͣ�̷߳���
	{
		timer = null;
	}
	public void run(){ //ÿ��һ���ӣ�ˢ��һ�λ���ķ���
		while (timer != null) 
		{
			try { Thread.sleep(1000); }
			catch (InterruptedException e) {}
			repaint(); //����paint()�����ػ�ʱ��
		}
		timer = null; 
	}
	public void update(Graphics g) //��д�÷�����Ϊ��������������
	{
		paint(g); 
	}
}

public class DateClock extends Frame
{

	private static final long serialVersionUID = 1L;
	Calen p1=new Calen();
	Panel p2=new Panel();
	Panel p3=new Panel();
	Panel p4=new Panel();
	Panel p5=new Panel();
	Panel p7=new Panel();
	Panel p8=new Panel();
	Cpanel p6=new Cpanel();
	public DateClock(){
		super("ʱ��������");
		setBackground(Color.LIGHT_GRAY);
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				System.exit(0);
			}
		});
		setSize(600, 400);
		setLayout(new GridLayout(1,2));
		//p1.setSize(100,60);
		add(p1);
		add(p2);
		p2.setLayout(new BorderLayout());
		p2.add(p3, "Center");
		p2.add(p4, "South");
		p3.setLayout(new BorderLayout());
		p3.add(p5, "North");p3.add(p6, "Center");
		p4.setLayout(new GridLayout(2,1));
		p4.add(p7);
		p4.add(p8);
		p5.add(p6.l2);
		p7.add(p6.l3);
		p8.add(p6.l1);
		LocalTime time=LocalTime.now();
		LocalDate date=LocalDate.now();
		int s = time.getSecond();
		int m = time.getMinute();
		int h = time.getHour();
		p6.l1.setText(h+":"+m+":"+s);
		p6.l2.setText(date.toString());
		p6.l3.setText((date.getDayOfWeek()).toString());
		p6.l1.setFont(new Font("Times New Roman ", Font.PLAIN, 20));
		p6.l2.setFont(new Font("Times New Roman ", Font.PLAIN, 20));
		p6.l3.setFont(new Font("Times New Roman ", Font.PLAIN, 20));
		setVisible(true);
	}

	public static void main (String[] args) {
		new DateClock();		
	}
}



class Calen extends Panel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Label l1=new Label();
	Label l2=new Label();
	Button b1,b2,b3,b4;
	Panel p1=new Panel();
	Panel p2=new Panel();
	Label []week=new Label[7];
	Label []day_list = new Label[42];
	Panel []pan= new Panel[7];
	CalendarBean calendar;
	LocalDate date=LocalDate.now();
	int year=date.getYear(),month=date.getMonthValue();
	String [] week_name={"��","һ","��","��","��","��","��"};
	public Calen()
	{
		int i ;
		b1=new Button("<");b2=new Button(">");
		b3=new Button("<");b4=new Button(">");
		b1.addActionListener(this);b2.addActionListener(this);
		b3.addActionListener(this);b4.addActionListener(this);


		calendar=new CalendarBean();
		calendar.setYear(year);
		calendar.setMonth(month);
		String []day=calendar.getCalendar();


		l1.setText(String.valueOf(date.getYear())+"��");
		l2.setText(String.valueOf(date.getMonthValue())+"��");
		l1.setFont(new Font("TimesRoman", Font.BOLD, 20));
		l2.setFont(new Font("TimesRoman", Font.BOLD, 20));

		this.setLayout(new BorderLayout());
		this.add(p1,"North");this.add(p2);
		p1.add(b3);p1.add(l1);p1.add(b4);p1.add(b1);p1.add(l2);p1.add(b2);
		p2.setLayout(new GridLayout(7,7));

		try{for( i = 0; i<7;i++)
		{
			this.week[i]=new Label("");
			this.week[i].setText(week_name[i]);
			this.week[i].setFont(new Font("TimesRoman", Font.BOLD, 14));
		}

		for( i = 0; i<7;i++)
		{
			p2.add(week[i]);
		}
		for(i=0;i<42;i++)
		{
			day_list[i]=new Label();
			day_list[i].setText(day[i]);
		}

		for(i=7;i<49;i++)
		{
			p2.add(day_list[i-7]);
		}

		}catch(Exception e){

		}

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b2)
		{
			month=month+1;
			if(month>12)
			{year=year+1;
			month=1;
			}
			calendar.setYear(year);
			calendar.setMonth(month);
			
			String day[]=calendar.getCalendar();

			for(int i=0;i<42;i++)
			{
			
				day_list[i].setText(day[i]);
				
			}
			l1.setText(String.valueOf(year)+"��");
			l2.setText(String.valueOf(month)+"��");
			l1.setFont(new Font("TimesRoman", Font.BOLD, 20));
			l2.setFont(new Font("TimesRoman", Font.BOLD, 20));
		}
		else if(e.getSource()==b1)
		{
			month=month-1;
			if(month<1)
			{year=year-1;
			month=12;}
			calendar.setYear(year);
			calendar.setMonth(month);
			
			String day[]=calendar.getCalendar();

			for(int i=0;i<42;i++)
			{
			day_list[i].setText(day[i]);
			}
			l1.setText(String.valueOf(year)+"��");
			l2.setText(String.valueOf(month)+"��");
			l1.setFont(new Font("TimesRoman", Font.BOLD, 20));
			l2.setFont(new Font("TimesRoman", Font.BOLD, 20));
		}
		else if(e.getSource()==b3)
		{
			year=year-1;
			calendar.setYear(year);
			calendar.setMonth(month);
			
			String day[]=calendar.getCalendar();

			for(int i=0;i<42;i++)
			{
			day_list[i].setText(day[i]);
			}
			l1.setText(String.valueOf(year)+"��");
			l2.setText(String.valueOf(month)+"��");
			l1.setFont(new Font("TimesRoman", Font.BOLD, 20));
			l2.setFont(new Font("TimesRoman", Font.BOLD, 20));
		}
		else if(e.getSource()==b4)
		{
			year=year+1;
			calendar.setYear(year);
			calendar.setMonth(month);
			
			String day[]=calendar.getCalendar();

			for(int i=0;i<42;i++)
			{
			day_list[i].setText(day[i]);
			}
			l1.setText(String.valueOf(year)+"��");
			l2.setText(String.valueOf(month)+"��");
			l1.setFont(new Font("TimesRoman", Font.BOLD, 20));
			l2.setFont(new Font("TimesRoman", Font.BOLD, 20));
		}


	}

}
class CalendarBean
{
	String day[];
	int year=2005,month=0;
	public void setYear(int year)
	{
		this.year=year;
	}

	public int getYear()
	{
		return year;
	}

	public void setMonth(int month)
	{
		this.month=month;
	}

	public int getMonth()
	{
		return month;
	}

	public String[] getCalendar()
	{
		String a[]=new String[42];
		Calendar date=Calendar.getInstance();
		date.set(year,month-1,1);
		int week=date.get(Calendar.DAY_OF_WEEK)-1;
		int day=0;

		//�жϴ��·�
		if(month==1||month==3||month==5||month==7
				||month==8||month==10||month==12)
		{
			day=31;
		}

		//�ж�С��
		if(month==4||month==6||month==9||month==11)
		{
			day=30;
		}

		//�ж�ƽ��������
		if(month==2)
		{
			if(((year%4==0)&&(year%100!=0))||(year%400==0))
			{
				day=29;
			}

			else
			{
				day=28;
			}
		}

		for(int i=week,n=1;i<week+day;i++)
		{
			a[i]=String.valueOf(n) ;
			n++;
		}
		return a;
	}
}


