function MaxAjax(max = 6) {
    // max simultaneous ajax
    this.max = max;
    // current number of running ajax requests
    this.count = 0;
    // ajax requests to be sent
    this.taskQueue = [];
}

MaxAjax.prototype.ajax = function(requestObj, callback) {
    var self = this;
    var top;

    if (this.count < this.max) {
        this.count++;
        
        axios(requestObj)
            .then(function (response) {
                callback(response);

                self.count--;

                if (self.taskQueue.length > 0) {
                    top = self.taskQueue.shift();
                    self.ajax(top[0], top[1]);
                }
            })
            .catch(function (error) {
                callback(null, error);

                self.count--;
                
                if (self.taskQueue.length > 0) {
                    top = self.taskQueue.shift();
                    self.ajax(top[0], top[1]);
                }
            });
    } else {
        this.taskQueue.push([requestObj, callback]);
    }
}

MaxAjax.prototype.post = function(url, params, callback) {
    return this.ajax({
        method: 'post',
        url: url,
        data: params
    }, callback);
}

MaxAjax.prototype.get = function(url, callback) {
    return this.ajax({
        method: 'get',
        url: url,
    }, callback);
}