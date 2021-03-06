class MenuComponent { 
  constructor() {
    this.computedLogTime()
    this.departureLog= ""
    this.arrivalLog= ""
  }

  computedLogTime(date) {
    const seconds = Math.floor((new Date() - date) / 1000);
    let interval = Math.floor(seconds / 31536000);
    if (interval > 1) return interval + "년";
    interval = Math.floor(seconds / 2592000);
    if (interval > 1) return interval + "달";
    interval = Math.floor(seconds / 86400);
    if (interval > 1) return interval + "일";
    interval = Math.floor(seconds / 3600);
    if (interval > 1) return interval + "시간";
    interval = Math.floor(seconds / 60);
    if (interval > 1) return interval + "분";

    return Math.floor(seconds) + "초";
  }

  columnTitleChange(data) {
    let target = ''
    switch(data) {
      case 1:
        target = '할 일'
        break;
      case 2:
        target = '하는중'
        break;
      case 3:
        target = '다했음'
        break;
    }
    return target
  }

  render(logData) {
    const logList = logData.reduce((list, log) => {
      log.departure == null ?
        this.defaultLog = `to ${this.columnTitleChange(log.arrival)}` : 
        (
          this.defaultLog = `to ${this.columnTitleChange(log.departure)} 
          from ${this.columnTitleChange(log.arrival)}`
        )
      let timeStamp = `${this.computedLogTime(new Date(log.createdTime))} 전`
      list += `
      <div class="ui feed" data-id=${log.id}>
            <div class="event">
              <div class="label">
                <img src="./assets/image/cat.jpeg" />
              </div>
              <div class="content">
                <div class="date">
                  <font style="vertical-align: inherit;">
                    ${timeStamp}
                  </font>
                </div>
                <div class="summary">
                  <a>
                    <font style="vertical-align: inherit;">@${log.author}</font>
                  </a>
                    <font style="vertical-align: inherit;">${log.action}</font>
                  <a>
                    <font style="vertical-align: inherit;">
                      ${log.targetName}
                    </font>
                  </a>
                  <font style="vertical-align: inherit;">
                    ${this.defaultLog}
                  </font>
                </div>
              </div>
            </div>
          </div>
      `
      return list
    } ,"") 
    return `
      <div class="menu-container">
        <div class="menu-area">
          <i class="bars icon"></i>
          <span>Menu</span>
        </div>
        <div class="menu-noti">
          <i class="bell icon"></i>
          <span>Activity</span>
        </div>
        <div class="feed-container">${logList}</div>
        <button class="menu-close-btn">
          <i class="x icon"></i>
        </button>
      </div>
    `
  }

  addLogMessage({time, event, title, column}) {
    const addEventMessage = `
    <div class="ui feed">
          <div class="event">
            <div class="label">
              <img src="./assets/image/cat.jpeg" />
            </div>
            <div class="content">
              <div class="date">
                <font style="vertical-align: inherit;">
                  ${time} 
                </font>
              </div>
              <div class="summary">
                <a>
                  <font style="vertical-align: inherit;">@ttozzi</font>
                </a>
                  <font style="vertical-align: inherit;"> ${event} </font>
                <a>
                  <font style="vertical-align: inherit;">
                    ${title}
                  </font>
                </a>
                <font style="vertical-align: inherit;">
                  to ${column}
                </font>
              </div>
            </div>
          </div>
        </div>
    `
    document.querySelector('.feed-container').insertAdjacentHTML('afterbegin', addEventMessage)
  }
}

export default MenuComponent