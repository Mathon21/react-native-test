//
//  TestModal.m
//  TestModal
//
//  Created by 马东 on 2019/7/25.
//  Copyright © 2019 马东. All rights reserved.
//

#import "TestModal.h"
#import "RCTLog.h"

static NSString * const kTestNotificationName = @"kTestNotificationName";
static NSString * const TestEventName = @"TestEventName";

@implementation TestModal

@synthesize bridge = _bridge;

//此处不能使用OC的字符串，直接输入就行了
RCT_EXPORT_MODULE(TestModal);

//测试方法导出
RCT_EXPORT_METHOD(testPrint:(NSString *)name info:(NSDictionary *)info) {
    RCTLogInfo(@"%@: %@", name, info);
}

//测试回调函数
RCT_EXPORT_METHOD(getNativeClass:(RCTResponseSenderBlock)callback) {
    callback(@[NSStringFromClass([self class])]);
}

//Promiss
RCT_REMAP_METHOD(testRespondMethod,
                 name:(NSString *)name
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject) {
    if([self respondsToSelector:NSSelectorFromString(name)]) {
        resolve(@YES);
    }
    else {
        reject(@"-1001", @"not respond this method", nil);
    }
}

//线程
- (dispatch_queue_t)methodQueue {
    return dispatch_queue_create("com.liuchungui.demo", DISPATCH_QUEUE_SERIAL);
}


//导出常量
- (NSDictionary *)constantsToExport {
    return @{ @"BGModuleName" : @"TestModal",
              TestEventName: TestEventName
              };
}

@end
