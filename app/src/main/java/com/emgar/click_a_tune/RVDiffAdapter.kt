//package com.emgar.click_a_tune
//
//import android.graphics.Color
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//
//
//// Pass in a function called clickListener that takes a view and a songName
//// as parameters.  Call clickListener when the row is clicked.
//class RVDiffAdapter(private val viewModel: MainViewModel,
//                    private val clickListener: (songIndex : Int)->Unit)
//    // https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter
//    // Slick adapter that provides submitList, so you don't worry about how to update
//    // the list, you just submit a new one when you want to change the list and the
//    // Diff class computes the smallest set of changes that need to happen.
//    // NB: Both the old and new lists must both be in memory at the same time.
//    // So you can copy the old list, change it into a new list, then submit the new list.
//    : ListAdapter<SongInfo,
//        RVDiffAdapter.ViewHolder>(Diff())
//{
//    companion object {
//        val TAG = "RVDiffAdapter"
//    }
//    var selectedPos = viewModel.currentIndex
//
//    // ViewHolder pattern holds row binding
//    inner class ViewHolder(val songRowBinding : SongRowBinding)
//        : RecyclerView.ViewHolder(songRowBinding.root) {
//            /// XXX
//        init {
//            songRowBinding.root.setOnClickListener {
//                val newPosition = bindingAdapterPosition
//                if (newPosition != RecyclerView.NO_POSITION) {
//                    // unhiglight the old position
//                    if (selectedPos != RecyclerView.NO_POSITION) {
//                        notifyItemChanged(selectedPos)
//                    }
//                    // highlight the new position
//                    selectedPos = newPosition
//                    notifyItemChanged(selectedPos)
//                    clickListener(newPosition)
//            }
//            }
//        }
//        fun bind(songInfo: SongInfo) {
//            songRowBinding.songName.text = songInfo.name
//            songRowBinding.songDuration.text = songInfo.time
//            val highlightColor = if (selectedPos == bindingAdapterPosition) Color.YELLOW else Color.TRANSPARENT
//            songRowBinding.root.setBackgroundColor(highlightColor)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        //XXX Write me.
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = SongRowBinding.inflate(layoutInflater, parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        //XXX Write me.
//        val song = getItem(position)
//        holder.bind(song)
//    }
//
//    class Diff : DiffUtil.ItemCallback<SongInfo>() {
//        // Item identity
//        override fun areItemsTheSame(oldItem: SongInfo, newItem: SongInfo): Boolean {
//            return oldItem.hashCode() == newItem.hashCode()
//        }
//        // Item contents are the same, but the object might have changed
//        override fun areContentsTheSame(oldItem: SongInfo, newItem: SongInfo): Boolean {
//            return oldItem.name == newItem.name
//                    && oldItem.rawId == newItem.rawId
//                    && oldItem.time == newItem.time
//        }
//    }
//
//    fun setSelectedPosition(newPosition: Int) {
//        if (selectedPos != newPosition) {
//            notifyItemChanged(selectedPos) // remove hl
//            selectedPos = newPosition
//            notifyItemChanged(selectedPos) // highlight the new item
//        }
//    }
//}
//
