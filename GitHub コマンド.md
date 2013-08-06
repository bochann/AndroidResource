##GitHub コマンド


新しくディレクトリを作成し、そこに空のリポジトリを作成

    $ mkdir tutorial
    $ cd tutorial
    $ git init
      Initialized empty Git repository in     
      /Users/eguchi/Desktop/tutorial/.git/
    

ディレクトリにmyfile.txtという名前でファイルを作成し、コミット
    
    $ git add myfile.txt
    $ git commit -m "first commit"
      [master (root-commit) a73ae49] first commit
      1 files changed, 1 insertions(+), 0 deletions(-)
      create mode 100644 myfile.txt
    
ブランチは branch コマンドで作成

    $ git branch <branchname>
    
引数を指定せずにbranchコマンドを実行すると、ブランチの一覧を表示

    $ git branch
      issue1
    * master

ブランチのチェックアウト

    $ git checkout <branch>
    
ブランチの作成とチェックアウトをまとめて行う

    $ git checkout -b <branch>
    
addコマンドの説明を追加してからコミット

    $ git add myfile.txt
    $ git commit -m "addの説明を追加"
      [issue1 b2b23c4] addの説明を追加
      1 files changed, 1 insertions(+), 0 deletions(-)
    
ブランチのマージ

    $ git merge <commit>
    
指定したブランチがHEADの指しているブランチに取り込まれる

    $ git checkout master
      Switched to branch 'master'
    
ブランチを削除

    $ git branch -d <branchname>
    
ブランチが削除されたかを確認

    $ git branch
    * master

issue2ブランチとissue3ブランチを作成し、issue2ブランチをチェックアウト

    $ git branch issue2
    $ git branch issue3
    $ git checkout issue2
      Switched to branch 'issue2'
    $ git branch
    * issue2
      issue3
      master
      
pullコマンドの説明を追加

    $ git add myfile.txt
    $ git commit -m "pullの説明を追加"
      [issue3 e5f91ac] pullの説明を追加
      1 files changed, 2 insertions(+), 0 deletions(-)
    
masterブランチをチェックアウトした後、issue2ブランチをマージ

    $ git checkout master
      Switched to branch 'master'
    $ git merge issue2
      Updating b2b23c4..8f7aa27
      Fast-forward
      myfile.txt |    2 ++
      1 files changed, 2 insertions(+), 0 deletions(-)
    
さきほどのマージを取り消し

    $ git reset --hard HEAD~
    
ブランチをチェックアウトしてから、masterに対してrebaseを実行

    $ git checkout issue3
      Switched to branch 'issue3'
    $ git rebase master
      First, rewinding head to replay your work on top of it...
      Applying: pullの説明を追加
      Using index info to reconstruct a base tree...
      <stdin>:13: new blank line at EOF.
      +
      warning: 1 line adds whitespace errors.
      Falling back to patching base and 3-way merge...
      Auto-merging myfile.txt
      CONFLICT (content): Merge conflict in myfile.txt
      Failed to merge in the changes.
      Patch failed at 0001 pullの説明を追加

      When you have resolved this problem run "git rebase --
      continue".
      If you would prefer to skip this patch, instead run "git 
      rebase     
      --skip".
      To check out the original branch and stop rebasing run "git   
      rebase --abort".
    
rebaseコマンドに --continue オプションを指定して実行

    $ git add myfile.txt
    $ git rebase --continue
    Applying: pullの説明を追加
    
masterブランチをチェックアウトしてからマージを実行

    $ git checkout master
    Switched to branch 'master'
    $ git merge issue3
    Updating 8f7aa27..96a0ff0
    Fast-forward
    myfile.txt |    1 +
    1 files changed, 1 insertions(+), 0 deletions(-)
    
新しくディレクトリを作成し、そこに空のリポジトリを作成

    $ mkdir tutorial
    $ cd tutorial
    $ git init
    Initialized empty Git repository in     
    /Users/eguchi/Desktop/tutorial/.git/
    
ディレクトリに次の内容のmyfile.txtという名前でファイルを作成し、コミット

    $ git add myfile.txt
    $ git commit -m "first commit"
    [master (root-commit) a73ae49] first commit
    1 files changed, 1 insertions(+), 0 deletions(-)
    create mode 100644 myfile.txt
    
タグを追加

    $ git tag <tagname>
    
現在のHEADが指しているコミットにappleというタグを付ける

    $ git tag apple
    
タグの一覧

    $ git tag
      apple
      
タグ情報を含めて履歴を表示

    $ git log --decorate
      commit e7978c94d2104e3e0e6e4a5b4a8467b1d2a2ba19 (HEAD, tag:      
      apple, master)
      Author: yourname <yourname@yourmail.com>
      Date:   Wed Jul 18 16:43:27 2012 +0900

      first commit
      
注釈付きタグを追加

    $ git tag -a <tagname>
    
現在のHEADが指しているコミットにbananaという注釈付きタグを付ける

    $ git tag -am "サルでもわかるGit" banana
    
タグの一覧とコメントを表示

    $ git tag -n
      apple           first commit
      banana          サルでもわかるGit
      
タグを削除

    $ git tag -d <tagname>
    
logコマンドで履歴を確認

    $ git log
      commit 326fc9f70d022afdd31b0072dbbae003783d77ed
      Author: yourname <yourname@yourmail.com>
      Date:   Mon Jul 16 23:17:56 2012 +0900

      addの説明を追加
 
      commit 48eec1ddf73a7fb508ef664efd6b3d873631742f
      Author: yourname <yourname@yourmail.com>
      Date:   Mon Jul 16 23:16:14 2012 +0900

      first commit
      
オプションを追加してコミット

     $ git add sample.txt
     $ git commit --amend
     
logコマンドで履歴とコミットメッセージを確認

     $ git log
       commit e9d75a02e62814541ee0410d9c1d1bf47ab1c057
       Author: yourname <yourname@yourmail.com>
       Date:   Mon Jul 16 23:17:56 2012 +0900

       addとcommitの説明を追加

       commit 48eec1ddf73a7fb508ef664efd6b3d873631742f
       Author: yourname <yourname@yourmail.com>
       Date:   Mon Jul 16 23:16:14 2012 +0900

       first commit
       
logコマンドで履歴を確認

     $ git log
       commit 0d4a808c26908cd5fe4b6294a00150342d1a58be
       Author: yourname <yourname@yourmail.com>
       Date:   Mon Jul 16 23:19:26 2012 +0900

       pullの説明を追加

       commit 9a54fd4dd22dbe22dd966581bc78e83f16cee1d7
       Author: yourname <yourname@yourmail.com>
       Date:   Mon Jul 16 23:19:01 2012 +0900

       commitの説明を追加

       commit 326fc9f70d022afdd31b0072dbbae003783d77ed
       Author: yourname <yourname@yourmail.com>
       Date:   Mon Jul 16 23:17:56 2012 +0900

       addの説明を追加

       commit 48eec1ddf73a7fb508ef664efd6b3d873631742f
       Author: yourname <yourname@yourmail.com>
       Date:   Mon Jul 16 23:16:14 2012 +0900

       first commit
       
pullの説明を追加しているコミットを取り消し

     $ git revert HEAD
       [master d47bb1d] Revert "pullの説明を追加"
       1 files changed, 1 insertions(+), 2 deletions(-)
       
logコマンドで履歴も確認

     $ git log
        commit 7bcf5e3b6fc47e875ec226ce2b13a53df73cf626
        Author: yourname <yourname@yourmail.com>
        Date:   Wed Jul 18 15:46:28 2012 +0900

        Revert "pullの説明を追加"

        This reverts commit       
        0d4a808c26908cd5fe4b6294a00150342d1a58be.

        commit 0d4a808c26908cd5fe4b6294a00150342d1a58be
        Author: yourname <yourname@yourmail.com>
        Date:   Mon Jul 16 23:19:26 2012 +0900

        pullの説明を追加

        commit 9a54fd4dd22dbe22dd966581bc78e83f16cee1d7
        Author: yourname <yourname@yourmail.com>
        Date:   Mon Jul 16 23:19:01 2012 +0900

        commitの説明を追加

        commit 326fc9f70d022afdd31b0072dbbae003783d77ed
        Author: yourname <yourname@yourmail.com>
        Date:   Mon Jul 16 23:17:56 2012 +0900

        addの説明を追加

        commit 48eec1ddf73a7fb508ef664efd6b3d873631742f
        Author: yourname <yourname@yourmail.com>
        Date:   Mon Jul 16 23:16:14 2012 +0900

         first commit
      
logコマンドで履歴を確認

    $ git log
       commit 0d4a808c26908cd5fe4b6294a00150342d1a58be
       Author: yourname <yourname@yourmail.com>
       Date:   Mon Jul 16 23:19:26 2012 +0900

       pullの説明を追加

       commit 9a54fd4dd22dbe22dd966581bc78e83f16cee1d7
       Author: yourname <yourname@yourmail.com>
       Date:   Mon Jul 16 23:19:01 2012 +0900

       commitの説明を追加

       commit 326fc9f70d022afdd31b0072dbbae003783d77ed
       Author: yourname <yourname@yourmail.com>
       Date:   Mon Jul 16 23:17:56 2012 +0900

       addの説明を追加

       commit 48eec1ddf73a7fb508ef664efd6b3d873631742f
       Author: yourname <yourname@yourmail.com>
       Date:   Mon Jul 16 23:16:14 2012 +0900

       first commit
      
コミットを削除

    $ git reset --hard HEAD~~
       HEAD is now at 326fc9f addの説明を追加
      
ORIG_HEADにresetするとreset前の状態に戻す

    $ git reset --hard ORIG_HEAD
       HEAD is now at 0d4a808 pullの説明を追加
      
「commitの説明を追加」したコミットを取り出してmasterに追加

    $ git checkout master
       Switched to branch 'master'
    $ git cherry-pick 99daed2
       error: could not apply 99daed2... commit
       hint: after resolving the conflicts, mark the corrected paths
       hint: with 'git add <paths>' or 'git rm <paths>'
       hint: and commit the result with 'git commit'
      
競合箇所を修正してから、コミット

    $ git add sample.txt
    $ git commit
    
過去のコミットをまとめる

    $ git rebase -i HEAD~~
    
commit --amendで変更を保存

    $ git add sample.txt
    $ git commit --amend
    
このコミットでの作業が終了したことを知らせる

    $ git rebase --continue
    
rebaseした後で元に戻したくなった場合

    $git reset --hard ORIG_HEAD
    
masterブランチに移動した後、--squashオプションを指定してmergeを実行

    $ git checkout master
      Switched to branch 'master'
    $ git merge --squash issue1
      Auto-merging sample.txt
      CONFLICT (content): Merge conflict in sample.txt
      Squash commit -- not updating HEAD
      Automatic merge failed; fix conflicts and then commit the result.