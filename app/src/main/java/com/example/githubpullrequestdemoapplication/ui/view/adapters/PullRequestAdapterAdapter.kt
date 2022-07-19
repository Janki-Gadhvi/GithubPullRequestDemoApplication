import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubpullrequestdemoapplication.R
import com.example.githubpullrequestdemoapplication.api.models.PullRequest
import com.example.githubpullrequestdemoapplication.databinding.RvPullRequestBinding
import com.example.githubpullrequestdemoapplication.utils.CommonViews


class PullRequestAdapterAdapter(
    var pullRequests: List<PullRequest>,
    private val context: Context
) :
    RecyclerView.Adapter<PullRequestAdapterAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PullRequestAdapterAdapter.ViewHolder {
        val binding = RvPullRequestBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: PullRequestAdapterAdapter.ViewHolder,
        position: Int
    ) {
        with(holder) {
            with(pullRequests[position]) {

                context.getString(R.string.created_at, this.created_at)
                    .also { binding.tvCreatedAt.text = it }

                context.getString(R.string.closed_at, this.closed_at)
                    .also { binding.tvClosedAt.text = it }

                context.getString(R.string.by, this.user.login).also { binding.tvName.text = it }

                binding.tvTitle.text = this.title

                Glide.with(context)
                    .load(this.user.avatar_url)
                    .placeholder(R.drawable.ic_default_user)
                    .into(binding.ivProfilePicture)

            }
        }
    }


    override fun getItemCount(): Int {
        return pullRequests.size
    }

    inner class ViewHolder(val binding: RvPullRequestBinding) :
        RecyclerView.ViewHolder(binding.root)

}