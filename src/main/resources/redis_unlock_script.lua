if (redis.call('exists', KEYS[1]) == 0) then
    return nil;
end
if redis.call('get',KEYS[1]) == ARGV[1] then
    return redis.call('del',KEYS[1]);
else
    return 0;
end