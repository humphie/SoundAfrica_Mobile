package com.app.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.SoundFrica.R;
import com.app.protobuffers.Databuffers.Album;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class CollectionBaseAdapter extends BaseAdapter {

    private List<Album> albums = null;
    private LayoutInflater layoutInflater;
    private Context context;
    public CollectionBaseAdapter(Context context, List<Album> albums)
    {
        this.setLayoutInflater(LayoutInflater.from(context));
        this.albums = albums;

    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return albums.size();
    }

    @Override
    public Object getItem(int position) {
        return albums.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null; // My object representation of the ListView Item object
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.collection_view,null);
            holder.albumName = (TextView)convertView.findViewById(R.id.albumTitle);
            holder.artistName = (TextView)convertView.findViewById(R.id.artist);
            byte bitmap[] = albums.get(position).getCoverArt().toByteArray();
            holder.coverArt.setImageBitmap(BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length));
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }


        return convertView;
    }

    static class ViewHolder{

        TextView albumName;
        TextView artistName;
        ImageView coverArt;
    }
}
