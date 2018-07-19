package com.ishiqing.modules.greendao;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;
import com.sq.domain.bean.GreenFriend;
import com.sq.domain.bean.GreenUser;
import com.sq.domain.dao.DaoUtils;
import com.sq.domain.dao.GreenFriendDao;
import com.sq.domain.dao.GreenUserDao;
import com.sq.library.utils.GsonUtils;
import com.sq.library.utils.L;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * GreenDao 在组件化项目中的基本使用
 * <p>
 * Created by javakam on 2018-7-5 19:56:05
 */
public class GreenDaoM2MFragment extends BaseFragment {
    @BindView(R.id.content)
    TextView content;

    private GreenUserDao mUserDao;
    private GreenFriendDao mFriendDao;

    // 1
    private GreenUser greenUser;
    // N
    private List<GreenFriend> greenFriends = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_green_dao_m2m;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("GreenDao多对多", true);
        // 当 TextView内容过多时，可以上下滚动查看
        content.setMovementMethod(ScrollingMovementMethod.getInstance());

        mUserDao = DaoUtils.getDao().getGreenUserDao();
        mFriendDao = DaoUtils.getDao().getGreenFriendDao();
        greenUser = new GreenUser();
        greenUser.setUid("123456");
        greenUser.setUsername("小明");
    }

    @OnClick({R.id.btAddFriendList1, R.id.btAddFriendList2, R.id.btGetFriends})
    public void onViewClicked(View view) {
        greenFriends.clear();
        switch (view.getId()) {
            case R.id.btAddFriendList1:
                for (int i = 1; i <= 5; i++) {
                    GreenFriend friend = new GreenFriend();
                    friend.setFid("" + i);
                    friend.setUid(greenUser.getUid());
                    friend.setFname("小美" + i);
                    greenFriends.add(friend);
                }
                greenUser.setFriends(greenFriends);
                /*
                1.可见，数据并不是一下全插入的...所以，还好用自己本身的Dao去插入！
                 */
//                mFriendDao.insertOrReplaceInTx(greenFriends);
                mFriendDao.insertOrReplaceInTx(greenFriends);
                mUserDao.insertOrReplaceInTx(greenUser);
                Map<String, Object> map = new HashMap<>();
                map.put("data", mUserDao.loadAll()); // List<GreenUser>
                String result = GsonUtils.toJson(GsonUtils.map2Json(map));
                L.i("result: " + result);
                break;
            case R.id.btAddFriendList2:
                for (int i = 6; i <= 10; i++) {
                    GreenFriend friend = new GreenFriend();
                    friend.setFid("" + i);
                    friend.setUid(greenUser.getUid());
                    friend.setFname("小强" + i);
                    greenFriends.add(friend);
                }
                greenUser.setFriends(greenFriends);
                mFriendDao.insertOrReplaceInTx(greenFriends);
                mUserDao.insertOrReplaceInTx(greenUser);
                Map<String, Object> map2 = new HashMap<>();
                map2.put("data", mUserDao.loadAll());
                String result2 = GsonUtils.toJson(GsonUtils.map2Json(map2));
                L.i("result2: " + result2);
                break;
            case R.id.btGetFriends:
                L.d("friend size: " + mFriendDao.count());
                L.d("user size : " + mUserDao.count());
                List<GreenFriend> friends = mFriendDao.loadAll();
                /*
                2.同样，查询时候也不能直接使用 ①:N 中的①去做查询。
                  例如：不执行 user.setFriends(list) 时的结果：
                      {"data":[{"friends":[],"uid":"123456","username":"小明"}]}
                  获取不到任何信息，所以还是要用到 N（GreenFriend）自己的 Dao 去做查询，根据的是UID外键！
                 */
                //  推荐重新 new 一个新集合去存储数据，防止数据混乱
                List<GreenUser> userList = new ArrayList<>();
                List<GreenUser> users = mUserDao.loadAll();
                for (GreenUser user : users) {
                    List<GreenFriend> list = mFriendDao.queryBuilder()
                            .where(GreenFriendDao.Properties.Uid.eq(user.getUid())).build().list();
                    user.setFriends(list);
                }
                userList.addAll(users);
                Map<String, Object> map3 = new HashMap<>();
                map3.put("data", userList); // users
                L.ee(GsonUtils.map2Json(map3));
                content.setText("GreenUser: \n" + GsonUtils.map2Json(map3));
                mFriendDao.deleteAll();
                mUserDao.deleteAll();
                break;
        }
    }
}
