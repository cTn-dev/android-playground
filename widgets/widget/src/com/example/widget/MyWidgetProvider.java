package com.example.widget;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {
    @Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
	    for (int i = 0; i < appWidgetIds.length; i++) {
	        int appWidgetId = appWidgetIds[i];
	        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
	        
	        // create some random data
            int random_number = (new Random().nextInt(100));
            
            Log.w("Random Number", String.valueOf(random_number));
              
            // Set the text
            remoteViews.setTextViewText(R.id.update, String.valueOf(random_number));
            
            // Register an onClickListener (why does this update all?)
            Intent intent = new Intent(context, MyWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
            
            // Update view
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
	    }
    }
	  
	  
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Log.w("Widgets Deleted", String.valueOf(appWidgetIds.length));
      
        for (int i = 0; i < appWidgetIds.length; i++) {
            Log.w("Widget ID", String.valueOf(appWidgetIds[i]));  
        }
    }
}
