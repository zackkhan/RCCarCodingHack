/**
 * Copyright 2014 Magnus Woxblom
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.woxthebox.draglistview.sample;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.woxthebox.draglistview.BoardView;
import com.woxthebox.draglistview.DragItem;

import java.util.ArrayList;

public class BoardFragment extends Fragment {

    private static int sCreatedItems = 0;
    private BoardView mBoardView;
    private int mColumns;
    ArrayList<Pair<Long, String>> mItemArray = new ArrayList<>();
    ArrayList<Pair<Long, String>> mItemArray2 = new ArrayList<>();
    public static ArrayList<String> methodValuesRight = new ArrayList<String>();
    public static ArrayList<String> methodValuesLeft = new ArrayList<String>();

    public static BoardFragment newInstance() {
        return new BoardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board_layout, container, false);

        mBoardView = (BoardView) view.findViewById(R.id.board_view);
        mBoardView.setSnapToColumnsWhenScrolling(true);
        mBoardView.setSnapToColumnWhenDragging(true);
        mBoardView.setSnapDragItemToTouch(true);
        mBoardView.setCustomDragItem(new MyDragItem(getActivity(), R.layout.column_item));
        mBoardView.setBoardListener(new BoardView.BoardListener() {
            @Override
            public void onItemDragStarted(int column, int row) {
                Toast.makeText(mBoardView.getContext(), "Start - column: " + column + " row: " + row, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemChangedColumn(int oldColumn, int newColumn) {
                TextView itemCount1 = (TextView) mBoardView.getHeaderView(oldColumn).findViewById(R.id.item_count);
                itemCount1.setText(Integer.toString(mBoardView.getAdapter(oldColumn).getItemCount()));
                TextView itemCount2 = (TextView) mBoardView.getHeaderView(newColumn).findViewById(R.id.item_count);
                itemCount2.setText(Integer.toString(mBoardView.getAdapter(newColumn).getItemCount()));
            }
            

            @Override
            public void onItemDragEnded(int fromColumn, int fromRow, int toColumn, int toRow) {
                if (fromColumn != toColumn || fromRow != toRow) {
                    Toast.makeText(mBoardView.getContext(), "End - column: " + toColumn + " row: " + toRow, Toast.LENGTH_SHORT).show();
                }
                System.out.println("fuck to row " + toRow);
                System.out.println("fuck from row " + fromRow);
                System.out.println("fuck to col " + toColumn);
                System.out.println("fuck from col " + fromColumn);
                String value = getValue(fromColumn, fromRow);

                if (value.equals("for(){")) {
                    View v1 = LayoutInflater.from(BoardFragment.this.getContext()).inflate(R.layout.timedialog, null);
                    final EditText secondsText;
                    AlertDialog.Builder builder = new AlertDialog.Builder(BoardFragment.this.getContext());
                    builder.setMessage("How many times should we run this loop?");
                    builder.setView(v1);
                    AlertDialog alert = builder.create();
                    alert.show();
                    secondsText = (EditText) v1.findViewById(R.id.secondEditText);
                    int numLoops;
                    if (secondsText.getText().toString().equals("")) {
                        numLoops = 0;
                    } else {
                        numLoops = Integer.parseInt(secondsText.getText().toString());
                    }
                     value = "for(int i = 0;i < " + numLoops + ";i++){";
                }

                if (fromColumn == 0 && toColumn == 0){
                    methodValuesLeft.add(toRow, value);
                    methodValuesLeft.remove(fromRow);
                } else if (fromColumn == 0 && toColumn == 1){
                    methodValuesRight.add(toRow, value);
                    methodValuesLeft.remove(fromRow);
                } else if (fromColumn == 1 && toColumn == 0){
                    methodValuesLeft.add(toRow, value);
                    methodValuesRight.remove(fromRow);
                } else if (fromColumn == 1 && toColumn == 1){
                    methodValuesRight.add(toRow, value);
                    methodValuesRight.remove(fromRow);
                }
                for (String val: methodValuesLeft){
                    System.out.println("left " + val);
                }
                for (String val: methodValuesRight){
                    System.out.println("right " + val);
                }
            }
        });
        return view;
    }

    private String getValue(int col, int row){
        if (col == 0){
            return methodValuesLeft.get(row);
        } else {
            return methodValuesRight.get(row);
        }
    }

    public static void initMethodValuesLeft(String[] values){

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Board");

        addColumnList(0);
        addEmptyColumnList();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_board, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_disable_drag).setVisible(mBoardView.isDragEnabled());
        menu.findItem(R.id.action_enable_drag).setVisible(!mBoardView.isDragEnabled());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_disable_drag:
                mBoardView.setDragEnabled(false);
                getActivity().invalidateOptionsMenu();
                return true;
            case R.id.action_enable_drag:
                mBoardView.setDragEnabled(true);
                getActivity().invalidateOptionsMenu();
                return true;
            case R.id.action_add_column:
                addColumnList(0);
                return true;
            case R.id.action_remove_column:
                mBoardView.removeColumn(0);
                return true;
            case R.id.action_clear_board:
                mBoardView.clearBoard();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
private void addEmptyColumnList()
{
    String [] arrayofmethods = {};
    for (int i = 0; i < arrayofmethods.length; i++) {
        long id = sCreatedItems++;
        mItemArray2.add(new Pair<>(id, arrayofmethods[i]));
    }

    final int column = mColumns;
    final ItemAdapter listAdapter = new ItemAdapter(mItemArray2, R.layout.column_item, R.id.item_layout, true);
    final View header = View.inflate(getActivity(), R.layout.column_header, null);
    ((TextView) header.findViewById(R.id.text)).setText("Your Program");
    ((TextView) header.findViewById(R.id.item_count)).setText("" + mItemArray2.size());
    header.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            long id = sCreatedItems++;
            Pair item = new Pair<>(id, "Test " + id);
            mBoardView.addItem(column, 0, item, true);
            //mBoardView.moveItem(4, 0, 0, true);
            //mBoardView.removeItem(column, 0);
            //mBoardView.moveItem(0, 0, 1, 3, false);
            //mBoardView.replaceItem(0, 0, item1, true);
            ((TextView) header.findViewById(R.id.item_count)).setText("" + mItemArray2.size());
        }
    });

    mBoardView.addColumnList(listAdapter, header, false);
    mColumns++;
    for (Pair<Long, String> item : mItemArray2){
        methodValuesRight.add(item.second);
    }

}
    private void addColumnList(int col) {
        if (col == 0){
            for (int i = 0; i < methodValuesLeft.size(); i++) {
                long id = sCreatedItems++;
                mItemArray.add(new Pair<>(id, methodValuesLeft.get(i)));
            }
        } else if (col == 1){
            for (int i = 0; i < methodValuesRight.size(); i++) {
                long id = sCreatedItems++;
                mItemArray.add(new Pair<>(id, methodValuesRight.get(i)));
            }
        }

        final int column = mColumns;
        final ItemAdapter listAdapter = new ItemAdapter(mItemArray, R.layout.column_item, R.id.item_layout, true);
        final View header = View.inflate(getActivity(), R.layout.column_header, null);
        ((TextView) header.findViewById(R.id.text)).setText("Methods");
        ((TextView) header.findViewById(R.id.item_count)).setText("" + mItemArray.size());
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = sCreatedItems++;
                Pair item = new Pair<>(id, "Test " + id);
                mBoardView.addItem(column, 0, item, true);
                //mBoardView.moveItem(4, 0, 0, true);
                //mBoardView.removeItem(column, 0);
                //mBoardView.moveItem(0, 0, 1, 3, false);
                //mBoardView.replaceItem(0, 0, item1, true);
                ((TextView) header.findViewById(R.id.item_count)).setText("" + mItemArray.size());
            }
        });

        mBoardView.addColumnList(listAdapter, header, false);
        mColumns++;
        for (Pair<Long, String> item : mItemArray){
            methodValuesLeft.add(item.second);
        }
    }

    private static class MyDragItem extends DragItem {

        public MyDragItem(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindDragView(View clickedView, View dragView) {
            CharSequence text = ((TextView) clickedView.findViewById(R.id.text)).getText();
            ((TextView) dragView.findViewById(R.id.text)).setText(text);
            CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
            CardView clickedCard = ((CardView) clickedView.findViewById(R.id.card));

            dragCard.setMaxCardElevation(40);
            dragCard.setCardElevation(clickedCard.getCardElevation());
            // I know the dragView is a FrameLayout and that is why I can use setForeground below api level 23
            dragCard.setForeground(clickedView.getResources().getDrawable(R.drawable.card_view_drag_foreground));
        }

        @Override
        public void onMeasureDragView(View clickedView, View dragView) {
            CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
            CardView clickedCard = ((CardView) clickedView.findViewById(R.id.card));
            int widthDiff = dragCard.getPaddingLeft() - clickedCard.getPaddingLeft() + dragCard.getPaddingRight() -
                    clickedCard.getPaddingRight();
            int heightDiff = dragCard.getPaddingTop() - clickedCard.getPaddingTop() + dragCard.getPaddingBottom() -
                    clickedCard.getPaddingBottom();
            int width = clickedView.getMeasuredWidth() + widthDiff;
            int height = clickedView.getMeasuredHeight() + heightDiff;
            dragView.setLayoutParams(new FrameLayout.LayoutParams(width, height));

            int widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
            dragView.measure(widthSpec, heightSpec);
        }

        @Override
        public void onStartDragAnimation(View dragView) {
            CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
            ObjectAnimator anim = ObjectAnimator.ofFloat(dragCard, "CardElevation", dragCard.getCardElevation(), 40);
            anim.setInterpolator(new DecelerateInterpolator());
            anim.setDuration(ANIMATION_DURATION);
            anim.start();
        }

        @Override
        public void onEndDragAnimation(View dragView) {
            CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
            ObjectAnimator anim = ObjectAnimator.ofFloat(dragCard, "CardElevation", dragCard.getCardElevation(), 6);
            anim.setInterpolator(new DecelerateInterpolator());
            anim.setDuration(ANIMATION_DURATION);
            anim.start();
        }
    }
}
