package unii.entertainment.randomizer.boardgame.got.ui.adapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import unii.entertainment.randomizer.boardgame.got.repository.PlayerRepository;

//based on: https://medium.com/@zackcosborn/step-by-step-recyclerview-swipe-to-delete-and-undo-7bbae1fce27e
public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    @NonNull
    private PlayerPreferenceAdapter adapter;
    //   @NonNull
//    private Drawable icon;
    @NonNull
    private final ColorDrawable background;
    @NonNull
    private final PlayerRepository playerRepository;

    public SwipeToDeleteCallback(@NonNull PlayerPreferenceAdapter adapter, @NonNull PlayerRepository playerRepository,
                                 @ColorInt int color) {

        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
        //    icon = ContextCompat.getDrawable(adapter.getContext(),
        //           R.drawable.ic_delete_white_36);
        this.background = new ColorDrawable(color);

        this.playerRepository = playerRepository;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        playerRepository.removePlayer(position);

        adapter.deleteItem(position);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX,
                dY, actionState, isCurrentlyActive);
        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;
        if (dX > 0) { // Swiping to the right
            background.setBounds(itemView.getLeft(), itemView.getTop(),
                    itemView.getLeft() + ((int) dX) + backgroundCornerOffset,
                    itemView.getBottom());

        } else if (dX < 0) { // Swiping to the left
            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
        } else { // view is unSwiped
            background.setBounds(0, 0, 0, 0);
        }
        background.draw(c);
    }
}
