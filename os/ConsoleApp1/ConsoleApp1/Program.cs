

using System.Text.RegularExpressions;
Console.WriteLine(isntPicture("http://hpc.ssau.ru/files/doc/ssc_regnote.doc"));
 bool IsFile(string address)
{
    var m = Regex.Match(address, @"[/\w\s%""''\\[\]{}()+-]*\.([.\w]+)$");
    if (m.Success)
        return m.Groups[1].Value != "html";
    return false;
}

 bool isntPicture(string address)
{
    var m = Regex.Match(address, @"[/\w\s%""''\\[\]{}()+-]*\.([.\w]+)$");
    if (m.Success)
        return m.Groups[1].Value != "png"
            && m.Groups[1].Value != "jpg"
            && m.Groups[1].Value != "jpeg"
            && m.Groups[1].Value != "webm"
            && m.Groups[1].Value != "svg"
            && m.Groups[1].Value != "fraq";
    return false;
}