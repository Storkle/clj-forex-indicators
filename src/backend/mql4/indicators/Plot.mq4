//+------------------------------------------------------------------+
//|                                                         Plot.mq4 |
//|                      Copyright © 2010, MetaQuotes Software Corp. |
//|                                        http://www.metaquotes.net |
//+------------------------------------------------------------------+
#property copyright "Copyright © 2010, MetaQuotes Software Corp."
#property link      "http://www.metaquotes.net"

#property indicator_color1    LightBlue
#property indicator_chart_window
extern string file = "out.txt";
double Indicator[];
//+------------------------------------------------------------------+
//| Custom indicator initialization function                         |
//+------------------------------------------------------------------+
int init()
  {
//---- indicators
//----
   IndicatorBuffers(1);
   SetIndexStyle(0,DRAW_LINE);
   SetIndexBuffer(0,Indicator);
   return(0);
  }
//+------------------------------------------------------------------+
//| Custom indicator deinitialization function                       |
//+------------------------------------------------------------------+
int deinit()
  {
   return(0);
  
  }
  
//+------------------------------------------------------------------+
//| Custom indicator iteration function                              |
//+------------------------------------------------------------------+
int start()
  {
   
      //read file and plot!
      int handle = FileOpen(file,FILE_READ|FILE_CSV,' ');
      if (handle<0) {
         int error = GetLastError();
         Print("error: "+error);
         return(-1);
      }
      int amount = FileReadNumber(handle);
      Print("amount is "+amount);
      for (int i=0;i<amount;i++) 
        Indicator[i] = FileReadNumber(handle);
      Print("achieved it "+Indicator[0]);
      FileClose(handle);

   return(0);
  }
//+------------------------------------------------------------------+