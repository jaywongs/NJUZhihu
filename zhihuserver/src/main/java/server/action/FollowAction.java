package server.action;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import server.model.Follow;
import server.model.User;
import server.service.FollowService;
import server.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class FollowAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	JSONObject json = null;

	public Follow follow;

	public String hisname;
	public String myname;

	public String getHisname() {
		return hisname;
	}

	public void setHisname(String hisname) {
		this.hisname = hisname;
	}

	public String getMyname() {
		return myname;
	}

	public void setMyname(String myname) {
		this.myname = myname;
	}

	public FollowService followService;

	public UserService userService;

	public Follow getFollow() {
		return follow;
	}

	public void setFollow(Follow follow) {
		this.follow = follow;
	}

	public FollowService getFollowService() {
		return followService;
	}

	public void setFollowService(FollowService followService) {
		this.followService = followService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 添加关注
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addFollow() throws Exception {
		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		request.setCharacterEncoding("utf-8");

		hisname = request.getParameter("hisname");
		myname = request.getParameter("myname");
		User user_followed = userService.findByName(hisname);
		User user_mine = userService.findByName(myname);
		boolean isSelf = user_mine.getId().intValue() == user_followed.getId().intValue();
		if(isSelf) return ERROR;
		else if(followService.findFollow(user_followed.getId(), user_mine.getId()) != null) return ERROR;
		else {
			follow = new Follow(user_mine.getId(), user_followed.getId());
			user_followed.setFans(user_followed.getFans() + 1);
			userService.updateUser(user_followed);
			followService.addFollow(follow);

			//TODO MessageService 关注后需要发送通知给被关注者

			return SUCCESS;
		}
	}

	/**
	 * 取消关注
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteFollow() throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		request.setCharacterEncoding("utf-8");

		hisname = request.getParameter("hisname");
		myname = request.getParameter("myname");
		System.out.println("delete." + hisname + "delete." + myname);
		User user_followed = userService.findByName(hisname);
		User user_mine = userService.findByName(myname);
		followService.deleteFollow(user_followed.getId(), user_mine.getId());
		user_followed.setFans(user_followed.getFans() - 1);
		userService.updateUser(user_followed);

		return SUCCESS;
	}

	/**
	 * 返回 是否关注 信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String isFollow() throws Exception {
		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		request.setCharacterEncoding("utf-8");

		hisname = request.getParameter("hisname");
		myname = request.getParameter("myname");
		System.out.println("check." + hisname + "check." + myname);
		User user_followed = userService.findByName(hisname);
		User user_mine = userService.findByName(myname);
		if (followService.findFollow(user_followed.getId(), user_mine.getId()) != null) {
			JSONObject jsonn = new JSONObject();
			jsonn.put("isfollow", 1);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.print(jsonn);
			pw.flush();
			pw.close();
		}

		return SUCCESS;
	}
}
