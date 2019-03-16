var hostConfig = "172.19.240.226:8080"
Page({
  /**
   * 页面的初始数据
   */
  data: {
    hotMasters: [],
    newMasters: [],//数据库返回的检索所得用户信息
    isSearching: 0,//是否检索
    person: null,
    hiddenToast: true,
    follow: "关注"
  },

  onLoad: function () {
    this.refresh();
  },

  onShow: function () {
    this.refresh();
  },

  onHide: function () {
    this.setData({
      person: null,
    })
  },

  searchPerson: function (e) {
    var that = this;
    this.setData({
      person: e.detail.value
    })
    /**
   * 检索用户
   */
    if (this.data.person != null && this.data.person != '') {
      wx.request({
        url: 'http://' + hostConfig + '/queswerServer/searchPerson',
        data: {
          person: this.data.person
        },
        header: {
          "Content-Type": "applciation/json"
        },
        method: "GET",
        success: function (e) {
          that.setData({
            newMasters: e.data
          });
          console.log(e);
        },
      })
    } else {
      this.refresh();
    }
  },
  /**
   * 发送请求，从服务端返回名人信息
   */
  refresh: function () {
    var that = this;
    wx.request({
      url: 'http://' + hostConfig + '/queswerServer/listFamous',
      data: {},
      header: {
        "Content-Type": "applciation/json"
      },
      method: "GET",
      success: function (e) {
        that.setData({
          hotMasters: e.data
        });
        console.log(e);
      },
    })
    /**
   * 发送请求，从服务端返回关注的用户信息
   */
    wx.getUserInfo({
      success: function (res) {
        wx.request({
          url: 'http://' + hostConfig + '/queswerServer/listliked',
          data: {
            'user.username': res.userInfo.nickName,
            'user.avatarUrl': res.userInfo.avatarUrl,
          },
          header: {
            "Content-Type": "applciation/json"
          },
          method: "GET",
          success: function (e) {
            console.log(e)
            that.setData({
              newMasters: e.data
            });
          },
        })
      }
    })
  },

  toPerson: function (e) {
    var $data = e.currentTarget.dataset
    wx.navigateTo({
      url: '../quest/quest?img=' + $data.img + '&name=' + $data.name
    })
  },

  toastHidden: function () {
    var that = this;
    that.setData({
      hiddenToast: true
    })
  },
  freshPerson: function (e) {
    var that = this;
    wx.request({
      url: 'http://' + hostConfig + '/queswerServer/listFamous',
      data: {},
      header: {
        "Content-Type": "applciation/json"
      },
      method: "GET",
      success: function (e) {
        that.setData({
          hotMasters: e.data
        });
        console.log(e);
      },
    })
  },
  /**
   * 关注用户情况
   */
  followPerson: function (e) {
    var that = this;
    var $data = e.currentTarget.dataset
    if ($data.isfam == 1) {
      that.setData({
        isf: 1
      })
    }
    /**
   * 添加关注
   */
    wx.getUserInfo({
      success: function (res) {
        wx.request({
          url: 'http://' + hostConfig + '/queswerServer/addFollow',
          data: {
            'hisname': $data.name,
            'myname': res.userInfo.nickName,
          },
          header: {//请求头
            "Content-Type": "applciation/json"
          },
          method: "GET",
          success: function (e) {
            that.setData({
              hiddenToast: false,
              follow: "已关注"
            })
          },
        })
      }
    })
  },
  /**
   * 取消关注用户
   */
  deleteFollow: function (e) {
    var that = this;
    var $data = e.currentTarget.dataset
    wx.getUserInfo({
      success: function (res) {
        wx.request({
          url: 'http://' + hostConfig + '/queswerServer/deleteFollow',
          data: {
            'hisname': $data.name,
            'myname': res.userInfo.nickName,
          },
          header: {
            "Content-Type": "applciation/json"
          },
          method: "GET",
          success: function (e) {
            that.refresh();
          },
        })
      }
    })
  }
})
